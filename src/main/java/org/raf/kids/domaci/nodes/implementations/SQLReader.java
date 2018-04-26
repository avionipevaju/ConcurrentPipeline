package org.raf.kids.domaci.nodes.implementations;

import org.raf.kids.domaci.nodes.Input;
import org.raf.kids.domaci.transfer.Collection;
import org.raf.kids.domaci.transfer.Data;
import org.raf.kids.domaci.vo.Constants;
import org.raf.kids.domaci.vo.PipelineID;
import org.raf.kids.domaci.vo.State;

import java.sql.*;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SQLReader extends Input {

    private AtomicBoolean connectionOpen;
    private Connection connection;
    private Object connectionLock = new Object();
    private AtomicInteger tableIndex;

    public SQLReader(String name, int numberOfExecutingThreads) {
        super(name, numberOfExecutingThreads);
        connectionOpen = new AtomicBoolean(false);
        tableIndex = new AtomicInteger(1);
    }

    @Override
    public Collection call() throws Exception {
        Collection collection =  new Collection(new PipelineID(12, name));
        if(getNodeState().get().equals(State.WAITING)) {
            getNodeState().set(State.ACTIVE);
        }
        if(getNodeState().get().equals(State.ACTIVE)) {
            if(!connectionOpen.get()){
                synchronized (connectionLock) {
                    System.out.println("~~~Opening database connection~~~");
                    Class.forName("com.mysql.jdbc.Driver");
                    try {
                        connection = DriverManager.getConnection(parameters.get(Constants.DATABASE), parameters.get(Constants
                                .USERNAME), parameters.get(Constants.PASSWORD));
                        connectionOpen.set(true);
                    } catch (Exception e){
                        getNodeState().set(State.STOPPED);
                        System.out.println(e.getMessage());
                        System.out.println("~~~Stopping node: " + name +" ~~~");
                        return null;
                    }
                }
             } else if(connection != null) {
                Statement stmt = null;
                String query;
                try {
                    while(true) {
                        int id = tableIndex.getAndIncrement();
                        query = "SELECT * FROM `Users` WHERE id=" + id;
                        stmt = connection.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        Data data = new Data(new PipelineID(id, name));
                        if(!rs.next()){
                            break;
                        }
                        do{
                            String username = rs.getString("username");
                            int age = rs.getInt("age");
                            data.setValue("username", username);
                            data.setValue("age", age);
                            //System.out.println("RECORD: " + username + age);
                            collection.put(data);
                        }
                        while (rs.next());
                    }
                    return collection;
                } catch (SQLException e ) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("~~~Connection not established yet~~~");
                return null;
            }

        }
        return null;
    }

}

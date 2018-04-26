package org.raf.kids.domaci.nodes.implementations;

import org.raf.kids.domaci.nodes.Input;
import org.raf.kids.domaci.transfer.Collection;
import org.raf.kids.domaci.transfer.Data;
import org.raf.kids.domaci.vo.PipelineID;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SQLReader extends Input {

    private AtomicBoolean connectionOpen;
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Connection connection;
    private Object connectionLock = new Object();

    public SQLReader(String name, int numberOfExecutingThreads) {
        super(name, numberOfExecutingThreads);
        connectionOpen = new AtomicBoolean(false);
    }

    @Override
    public Collection call() throws Exception {
        System.out.println(name + getClass().getName());
        if(!connectionOpen.get()){
            synchronized (connectionLock) {
                System.out.println("ONE");
                Class.forName("com.mysql.jdbc.Driver");
                connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/raf","root","");
                connectionOpen.set(true);
            }
        }

        Thread.sleep(new Random().nextInt(1000));
        Collection collection = new Collection(new PipelineID(1));
        Data data = new Data(new PipelineID(12));
        data.setValue("TEST", 12);
        collection.put(data);
        return collection;
    }

}

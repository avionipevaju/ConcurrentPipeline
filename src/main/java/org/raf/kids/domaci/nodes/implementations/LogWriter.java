package org.raf.kids.domaci.nodes.implementations;

import org.raf.kids.domaci.nodes.Output;
import org.raf.kids.domaci.transfer.Collection;

import java.io.File;
import java.io.FileWriter;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LogWriter extends Output {

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private File logFile;
    private FileWriter fileWriter;

    public LogWriter(String name, int numberOfExecutingThreads) {
        super(name, numberOfExecutingThreads);
        logFile = new File("src/main/resources/log.txt");
        try {
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Collection call() throws Exception {
        while (!inputPipelineCollection.isDone()){ }
        try {
            while (!inputPipelineCollection.get().isEmpty()){
                readWriteLock.writeLock().lock();
                fileWriter = new FileWriter(logFile, true);
                fileWriter.append(inputPipelineCollection.get().take().getValue("user").toString()+'\n');
                fileWriter.close();
                readWriteLock.writeLock().unlock();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        //readWriteLock.writeLock().unlock();
        //System.out.println(name + ": " + inputPipelineCollection.get());
        return inputPipelineCollection.get();
    }
}

package org.raf.kids.domaci.nodes;

import org.raf.kids.domaci.transfer.Collection;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class WorkerJob implements Callable<Collection> {

    private Future<Collection> inputCollection;

    public WorkerJob(Future<Collection> inputCollection) {
        this.inputCollection = inputCollection;
    }

    @Override
    public Collection call() throws Exception {
        while (!inputCollection.isDone()) {

        }
        System.out.println("DONE");
        return inputCollection.get();
    }
}

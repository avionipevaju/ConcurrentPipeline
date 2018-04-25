package org.raf.kids.domaci.nodes;

import org.raf.kids.domaci.transfer.Collection;

import java.util.concurrent.Callable;

public class WorkerJob implements Callable<Collection> {

    private Collection inputCollection;

    public WorkerJob(Collection inputCollection) {
        this.inputCollection = inputCollection;
    }

    @Override
    public Collection call() throws Exception {
        return inputCollection;
    }
}

package org.raf.kids.domaci.nodes;

import org.raf.kids.domaci.transfer.Collection;
import org.raf.kids.domaci.transfer.Data;
import org.raf.kids.domaci.vo.PipelineID;
import org.raf.kids.domaci.vo.State;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

public class WorkerJob extends Node implements Callable<Collection> {

    private Future<Collection> inputCollection;

    public WorkerJob(Future<Collection> inputCollection) {
        this.inputCollection = inputCollection;
        setNodeState(new AtomicReference<>(State.WAITING));
    }

    @Override
    public Collection call() throws Exception {
        if(getNodeState().get().equals(State.WAITING)) {
            getNodeState().set(State.ACTIVE);
        }
        if(getNodeState().get().equals(State.ACTIVE)) {
            while (!inputCollection.isDone()) { }
            try {
                Collection input = inputCollection.get();
                if (input == null) {
                    System.out.println("Worker received empty input");
                    return null;
                } else {
                    while(!input.isEmpty()){
                        System.out.println(input.take());
                    }
                }
                return input;
            } catch (Exception e) {
                getNodeState().set(State.STOPPED);
                System.out.println(e.getMessage());
                System.out.println("~~~Stopping node: " + name +" ~~~");
                return null;
            }
        }

        return null;
    }
}

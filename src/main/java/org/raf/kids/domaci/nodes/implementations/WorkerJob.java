package org.raf.kids.domaci.nodes.implementations;

import org.raf.kids.domaci.nodes.Node;
import org.raf.kids.domaci.transfer.Collection;
import org.raf.kids.domaci.transfer.Data;
import org.raf.kids.domaci.vo.PipelineID;
import org.raf.kids.domaci.vo.State;

import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

public class WorkerJob extends Node implements Callable<Collection> {

    private Future<Collection> inputCollection;

    public WorkerJob(Future<Collection> inputCollection, HashMap<String, String > parameters) {
        this.inputCollection = inputCollection;
        setNodeState(new AtomicReference<>(State.WAITING));
        if(parameters.get("age") != null) {
            String [] paramList = parameters.get("age").split(",");


        }
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
                Collection output = input;
                if (input == null) {
                   // System.out.println("Worker received empty input");
                    return null;
                } else {
                    /*while(!input.isEmpty()){
                        //System.out.println(input.take());
                    }*/
                }
                return output;
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

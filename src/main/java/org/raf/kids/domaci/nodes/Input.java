package org.raf.kids.domaci.nodes;

import org.raf.kids.domaci.transfer.Collection;

import java.util.concurrent.Callable;

public class Input extends Node implements Callable<Collection> {

    public Input(String name, int numberOfExecutingThreads) {
        super(name, numberOfExecutingThreads);
    }

    @Override
    public Collection call() throws Exception {
        //System.out.println(name + getClass().getName());
        return null;
    }
}

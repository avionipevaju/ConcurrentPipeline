package org.raf.kids.domaci.nodes;

import org.raf.kids.domaci.transfer.Collection;

public class Output extends Node {

    public Output(String name, int numberOfExecutingThreads) {
        super(name, numberOfExecutingThreads);
    }


    @Override
    public Collection call() throws Exception {
        return null;
    }
}

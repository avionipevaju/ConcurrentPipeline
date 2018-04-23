package org.raf.kids.domaci.nodes;

import org.raf.kids.domaci.transfer.Collection;

public class Input extends Node {

    public Input(String name, int numberOfExecutingThreads) {
        super(name, numberOfExecutingThreads);
    }

    @Override
    public Collection call() throws Exception {
        return null;
    }
}

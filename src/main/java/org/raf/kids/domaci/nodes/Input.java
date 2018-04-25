package org.raf.kids.domaci.nodes;

import org.raf.kids.domaci.transfer.Collection;
import org.raf.kids.domaci.transfer.Data;
import org.raf.kids.domaci.vo.PipelineID;

import java.util.Random;

public class Input extends Node {

    int a = 0;

    public Input(String name, int numberOfExecutingThreads) {
        super(name, numberOfExecutingThreads);
    }

    @Override
    public Collection call() throws Exception {
        System.out.println(name + getClass().getName());
        return null;
    }
}

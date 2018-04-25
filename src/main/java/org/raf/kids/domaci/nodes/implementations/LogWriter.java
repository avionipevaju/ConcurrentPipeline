package org.raf.kids.domaci.nodes.implementations;

import org.raf.kids.domaci.nodes.Output;
import org.raf.kids.domaci.transfer.Collection;

public class LogWriter extends Output {
    public LogWriter(String name, int numberOfExecutingThreads) {
        super(name, numberOfExecutingThreads);
    }

    @Override
    public Collection call() throws Exception {
        while (!inputPipelineCollection.isDone()){

        }
        System.out.println(name + ": " + inputPipelineCollection.get());
        return inputPipelineCollection.get();
    }
}

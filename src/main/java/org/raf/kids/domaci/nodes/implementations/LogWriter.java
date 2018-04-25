package org.raf.kids.domaci.nodes.implementations;

import org.raf.kids.domaci.nodes.Output;
import org.raf.kids.domaci.transfer.Collection;
import org.raf.kids.domaci.vo.PipelineID;

public class LogWriter extends Output {
    public LogWriter(String name, int numberOfExecutingThreads) {
        super(name, numberOfExecutingThreads);
    }

    @Override
    public Collection call() throws Exception {
        while (!inputPipelineCollection.isDone()){ }
        System.out.println(name + ": " + inputPipelineCollection.get().peek(new PipelineID(12)));
        return inputPipelineCollection.get();
    }
}

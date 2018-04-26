package org.raf.kids.domaci.nodes.implementations;

import org.raf.kids.domaci.nodes.Output;
import org.raf.kids.domaci.transfer.Collection;
import org.raf.kids.domaci.vo.PipelineID;

public class GuiDisplay extends Output {
    public GuiDisplay(String name, int numberOfExecutingThreads) {
        super(name, numberOfExecutingThreads);
    }

    @Override
    public Collection call() throws Exception {
        while (!inputPipelineCollection.isDone()){ }
        //System.out.println(name + ": " + inputPipelineCollection.get());
        return inputPipelineCollection.get();
    }
}

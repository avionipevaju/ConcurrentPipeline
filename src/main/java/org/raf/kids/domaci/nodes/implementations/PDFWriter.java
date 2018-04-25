package org.raf.kids.domaci.nodes.implementations;

import org.raf.kids.domaci.nodes.Output;
import org.raf.kids.domaci.transfer.Collection;

public class PDFWriter extends Output {

    public PDFWriter(String name, int numberOfExecutingThreads) {
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

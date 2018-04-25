package org.raf.kids.domaci.nodes;

import org.raf.kids.domaci.transfer.Collection;

public class Output extends Node {

    private Collection inputPipelineCollection;

    public Output(String name, int numberOfExecutingThreads) {
        super(name, numberOfExecutingThreads);
    }

    @Override
    public Collection call() throws Exception {
        System.out.println(name + ": " + inputPipelineCollection);
        return null;
    }

    public Collection getInputPipelineCollection() {
        return inputPipelineCollection;
    }

    public void setInputPipelineCollection(Collection inputPipelineCollection) {
        this.inputPipelineCollection = inputPipelineCollection;
    }
}

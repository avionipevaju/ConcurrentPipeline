package org.raf.kids.domaci.nodes;

import org.raf.kids.domaci.transfer.Collection;

import java.util.concurrent.Future;

public class Output extends Node {

    protected Future<Collection> inputPipelineCollection;

    public Output(String name, int numberOfExecutingThreads) {
        super(name, numberOfExecutingThreads);
    }

    @Override
    public Collection call() throws Exception {
        while (!inputPipelineCollection.isDone()){

        }
        System.out.println(name + ": " + inputPipelineCollection.get());
        return inputPipelineCollection.get();
    }

    public Future<Collection> getInputPipelineCollection() {
        return inputPipelineCollection;
    }

    public void setInputPipelineCollection(Future<Collection> inputPipelineCollection) {
        this.inputPipelineCollection = inputPipelineCollection;
    }
}

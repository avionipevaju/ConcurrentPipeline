package org.raf.kids.domaci.nodes;

import java.util.ArrayList;
import java.util.List;

public class Worker extends Node {

    private Worker previousOperation;
    private Worker nextOperation;
    private List<Input> inputNodes;
    private List<Output> outputNodes;

    public Worker(String name, int numberOfExecutingThreads) {
        super(name, numberOfExecutingThreads);
        inputNodes = new ArrayList<>();
        outputNodes = new ArrayList<>();
    }

    public Worker(String name, int numberOfExecutingThreads, Worker previousOperation, Worker nextOperation) {
        super(name, numberOfExecutingThreads);
        this.previousOperation = previousOperation;
        this.nextOperation = nextOperation;
        inputNodes = new ArrayList<>();
        outputNodes = new ArrayList<>();
    }

    @Override
    public void run() {
        for(Input node: inputNodes){
            node.run();
        }
        for(Output node: outputNodes){
            node.run();
        }
    }

    public Input getInputNode(int index) {
        if (inputNodes ==  null || index < 0 || index >= inputNodes.size()) {
            return null;
        }
        return inputNodes.get(index);
    }

    public boolean addInputNode(Input node) {
        if (inputNodes == null) {
            inputNodes =  new ArrayList<>();
        }
        if (node == null) {
            return false;
        }
        inputNodes.add(node);
        return true;
    }

    public Output getOutputNode(int index) {
        if (outputNodes ==  null || index < 0 || index >= outputNodes.size()) {
            return null;
        }
        return outputNodes.get(index);
    }

    public boolean addOutputNode(Output node) {
        if (outputNodes == null) {
            outputNodes =  new ArrayList<>();
        }
        if (node == null) {
            return false;
        }
        outputNodes.add(node);
        return true;
    }

    public Worker getPreviousOperation() {
        return previousOperation;
    }

    public Worker getNextOperation() {
        return nextOperation;
    }

    public void setPreviousOperation(Worker previousOperation) {
        this.previousOperation = previousOperation;
    }

    public void setNextOperation(Worker nextOperation) {
        this.nextOperation = nextOperation;
    }

    public List<Input> getInputNodes() {
        return inputNodes;
    }

    public List<Output> getOutputNodes() {
        return outputNodes;
    }

    @Override
    public String toString() {
        return "Worker{" +
                ", \ninputNodes=" + inputNodes +
                ", \noutputNodes=" + outputNodes +
                ", \nname='" + name + '\'' +
                ", \nnumberOfExecutingThreads=" + numberOfExecutingThreads +
                ", \nparameters=" + parameters +
                '}';
    }

}

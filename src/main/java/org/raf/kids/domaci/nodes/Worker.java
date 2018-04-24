package org.raf.kids.domaci.nodes;

import org.raf.kids.domaci.transfer.Collection;
import org.raf.kids.domaci.vo.PipelineID;
import org.raf.kids.domaci.vo.State;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Worker extends Node {

    private Worker previousOperation;
    private Worker nextOperation;
    private List<Input> inputNodes;
    private List<Output> outputNodes;
    private Collection inputPipelineCollection;
    private boolean startNode;
    private boolean endNode;

    public Worker(String name, int numberOfExecutingThreads) {
        super(name, numberOfExecutingThreads);
        inputNodes = new ArrayList<>();
        outputNodes = new ArrayList<>();
    }

    public Worker(String name, int numberOfExecutingThreads, Collection inputPipelineCollection) {
        super(name, numberOfExecutingThreads);
        this.inputPipelineCollection = inputPipelineCollection;
    }

    public Worker(String name, int numberOfExecutingThreads, Worker previousOperation, Worker nextOperation) {
        super(name, numberOfExecutingThreads);
        this.previousOperation = previousOperation;
        this.nextOperation = nextOperation;
        inputNodes = new ArrayList<>();
        outputNodes = new ArrayList<>();
    }

    @Override
    public Collection call() throws Exception {
        setNodeState(State.ACTIVE);
        List<Input> inputNodeThreads = new ArrayList<>();
        List<Output> outputNodeThreads = new ArrayList<>();
        for(Input node: inputNodes) {
            for (int i = 0; i< node.getNumberOfExecutingThreads();i++){
                inputNodeThreads.add(node);
            }
        }
        for(Output node: outputNodes) {
            for (int i = 0; i< node.getNumberOfExecutingThreads();i++){
                outputNodeThreads.add(node);
            }
        }
        ExecutorService inputNodeExecutorService = Executors.newCachedThreadPool();
        ExecutorService outputNodeExecutorService = Executors.newCachedThreadPool();
        List<Future<Collection>> results = inputNodeExecutorService.invokeAll(inputNodeThreads);
        for (Future<Collection> future: results) {
            System.out.println(future.get().peek(new PipelineID(12)).getValue("TEST"));
        }
        return null;
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

    public Collection getInputPipelineCollection() {
        return inputPipelineCollection;
    }

    public void setInputPipelineCollection(Collection inputPipelineCollection) {
        this.inputPipelineCollection = inputPipelineCollection;
    }

    public boolean isStartNode() {
        return startNode;
    }

    public void setStartNode(boolean startNode) {
        this.startNode = startNode;
    }

    public boolean isEndNode() {
        return endNode;
    }

    public void setEndNode(boolean endNode) {
        this.endNode = endNode;
    }

    @Override
    public String toString() {
        return name +"[" +
                "  \npreviousOperation=" + previousOperation +
                ", \nnextOperation=" + nextOperation +
                ", \ninputNodes=" + inputNodes +
                ", \noutputNodes=" + outputNodes +
                ", \ninputPipelineCollection=" + inputPipelineCollection +
                ", \nname='" + name + '\'' +
                ", \nnumberOfExecutingThreads=" + numberOfExecutingThreads +
                ", \nnodeState=" + nodeState +
                ", \nparameters=" + parameters +
                ", \nstartNode=" + startNode +
                ", \nendNode=" + endNode +
                '}';
    }
}

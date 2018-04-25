package org.raf.kids.domaci.nodes;

import org.raf.kids.domaci.transfer.Collection;
import org.raf.kids.domaci.vo.PipelineID;
import org.raf.kids.domaci.vo.State;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Worker extends Node {

    private Worker previousOperation;
    private Worker nextOperation;
    private List<Input> inputNodes;
    private List<Output> outputNodes;
    private Future<Collection> inputPipelineCollection;
    private boolean startNode;
    private boolean endNode;

    public Worker(String name, int numberOfExecutingThreads) {
        super(name, numberOfExecutingThreads);
        inputNodes = new ArrayList<>();
        outputNodes = new ArrayList<>();
    }

    public Worker(String name, int numberOfExecutingThreads, Future<Collection> inputPipelineCollection) {
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
        ExecutorService inputExecutorService = Executors.newCachedThreadPool();
        ExecutorService workerExecutorService = Executors.newFixedThreadPool(numberOfExecutingThreads);
        HashMap<Output, ExecutorService> outputExecutorThreads = new HashMap<>();

        for(Output output: outputNodes) {
            for (int i = 0; i< output.getNumberOfExecutingThreads();i++){
                outputExecutorThreads.put(output, Executors.newFixedThreadPool(output.getNumberOfExecutingThreads()));
            }

        }

        for(Input input: inputNodes) {
            for (int i = 0; i< input.getNumberOfExecutingThreads();i++){
                Future<Collection> inputResult = inputExecutorService.submit(input);
                Future<Collection> workerResult = workerExecutorService.submit(new WorkerJob(inputResult));
                if(nextOperation != null) {
                    nextOperation.setInputPipelineCollection(workerResult);
                    nextOperation.call();
                }
                for(Output output: outputExecutorThreads.keySet()){
                    output.setInputPipelineCollection(workerResult);
                    outputExecutorThreads.get(output).submit(output);
                }
            }
        }

        if(inputPipelineCollection != null) {
            while(!inputPipelineCollection.isDone()) { }
            Future<Collection> workerResult = workerExecutorService.submit(new WorkerJob(inputPipelineCollection));
            if(nextOperation != null) {
                nextOperation.setInputPipelineCollection(workerResult);
                nextOperation.call();
            }
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

    public Future<Collection> getInputPipelineCollection() {
        return inputPipelineCollection;
    }

    public void setInputPipelineCollection(Future<Collection> inputPipelineCollection) {
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
        String prev = previousOperation!=null?previousOperation.getName():"null";
        String next = nextOperation!=null?nextOperation.getName():"null";
        return name +"[" +
                "  \npreviousOperation=" +  prev +
                ", \nnextOperation=" +next +
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

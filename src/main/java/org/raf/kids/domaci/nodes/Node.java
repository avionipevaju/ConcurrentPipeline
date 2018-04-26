package org.raf.kids.domaci.nodes;

import org.raf.kids.domaci.vo.State;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public abstract class Node {

    protected String name;
    protected int numberOfExecutingThreads;
    protected AtomicReference<State> nodeState;
    protected HashMap<String, String> parameters;

    public Node(String name, int numberOfExecutingThreads) {
        this.name = name;
        this.numberOfExecutingThreads = numberOfExecutingThreads;
        this.nodeState = new AtomicReference<>(State.WAITING);
        parameters = new HashMap<>();
    }

    public Node() {
    }

    public Object getParameter(String parameterKey) {
        if (parameterKey == null) {
            return null;
        }
        if (parameters == null) {
            parameters = new HashMap<>();
        }
        return parameters.get(parameterKey);
    }

    public boolean addParameter(String parameterKey, String parameterValue) {
        if (parameterKey == null || parameterValue == null) {
            return false;
        }
        if (parameters == null) {
            return false;
        }
        parameters.put(parameterKey, parameterValue);
        return true;
    }

    public int getNumberOfExecutingThreads() {
        return numberOfExecutingThreads;
    }

    public void setNumberOfExecutingThreads(int numberOfExecutingThreads) {
        this.numberOfExecutingThreads = numberOfExecutingThreads;
    }

    public HashMap<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(HashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AtomicReference<State> getNodeState() {
        return nodeState;
    }

    public void setNodeState(AtomicReference<State> nodeState) {
        this.nodeState = nodeState;
    }

    @Override
    public String toString() {
        return  "\n\tname='" + name + '\'' +
                ", \n\tnumberOfExecutingThreads=" + numberOfExecutingThreads +
                ", \n\tnodeState=" + nodeState +
                ", \n\tparameters=" + parameters +
                '}';
    }
}

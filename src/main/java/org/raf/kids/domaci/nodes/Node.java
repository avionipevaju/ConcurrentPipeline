package org.raf.kids.domaci.nodes;

import org.raf.kids.domaci.transfer.Collection;

import java.util.HashMap;
import java.util.concurrent.Callable;

public abstract class Node implements Callable<Collection> {

    protected String name;
    protected int numberOfExecutingThreads;
    protected HashMap<String, Object> parameters;

    public Node(String name, int numberOfExecutingThreads) {
        this.name = name;
        this.numberOfExecutingThreads = numberOfExecutingThreads;
        parameters = new HashMap<>();
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

    public HashMap<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(HashMap<String, Object> parameters) {
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", \n\tnumberOfExecutingThreads=" + numberOfExecutingThreads +
                ", \n\tparameters=" + parameters +
                '}';
    }
}

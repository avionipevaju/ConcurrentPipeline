package org.raf.kids.domaci.nodes;

public class Output extends Node {

    public Output(String name, int numberOfExecutingThreads) {
        super(name, numberOfExecutingThreads);
    }

    @Override
    public void run() {
        System.out.println(name);
    }

}

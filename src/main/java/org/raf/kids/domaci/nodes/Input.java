package org.raf.kids.domaci.nodes;

public class Input extends Node {

    public Input(String name, int numberOfExecutingThreads) {
        super(name, numberOfExecutingThreads);
    }

    @Override
    public void run() {
        System.out.println(name);
    }

}

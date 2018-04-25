package org.raf.kids.domaci.nodes;

import org.raf.kids.domaci.transfer.Collection;
import org.raf.kids.domaci.transfer.Data;
import org.raf.kids.domaci.vo.PipelineID;

import java.util.Random;

public class Input extends Node {

    int a = 0;

    public Input(String name, int numberOfExecutingThreads) {
        super(name, numberOfExecutingThreads);
    }

    @Override
    public Collection call() throws Exception {
        Thread.sleep(new Random().nextInt(1000));
        Collection collection = new Collection(new PipelineID(1));
        Data data = new Data(new PipelineID(12));
        data.setValue("TEST", a++);
        collection.put(data);
        return collection;
    }
}

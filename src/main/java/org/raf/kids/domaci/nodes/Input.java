package org.raf.kids.domaci.nodes;

import org.raf.kids.domaci.transfer.Collection;
import org.raf.kids.domaci.transfer.Data;
import org.raf.kids.domaci.vo.PipelineID;

public class Input extends Node {

    int a = 0;

    public Input(String name, int numberOfExecutingThreads) {
        super(name, numberOfExecutingThreads);
    }

    @Override
    public Collection call() throws Exception {
        Data data = new Data(new PipelineID(12));
        data.setValue("TEST", a++);
        Collection collection = new Collection(new PipelineID(1));
        collection.put(data);
        return collection;
    }
}

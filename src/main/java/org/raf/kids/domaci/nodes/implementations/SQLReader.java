package org.raf.kids.domaci.nodes.implementations;

import org.raf.kids.domaci.nodes.Input;
import org.raf.kids.domaci.transfer.Collection;
import org.raf.kids.domaci.transfer.Data;
import org.raf.kids.domaci.vo.PipelineID;

import java.util.Random;

public class SQLReader extends Input {

    public SQLReader(String name, int numberOfExecutingThreads) {
        super(name, numberOfExecutingThreads);
    }

    @Override
    public Collection call() throws Exception {
        Thread.sleep(200);
        System.out.println(name + getClass().getName());
        Thread.sleep(new Random().nextInt(1000));
        Collection collection = new Collection(new PipelineID(1));
        Data data = new Data(new PipelineID(12));
        data.setValue("TEST", 12);
        collection.put(data);
        return collection;
    }

}

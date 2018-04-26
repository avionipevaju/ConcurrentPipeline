package org.raf.kids.domaci.transfer;

import org.raf.kids.domaci.vo.PipelineID;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class Collection implements PipelineCollection {

    private PipelineID pipelineCollectionId;
    private BlockingQueue<PipelineData> transferCollection;

    public Collection(PipelineID pipelineCollectionId) {
        this.pipelineCollectionId = pipelineCollectionId;
        transferCollection = new ArrayBlockingQueue<>(10);
    }

    @Override
    public PipelineID getId() {
        return pipelineCollectionId;
    }

    @Override
    public PipelineData peek(PipelineID id) {
        if (transferCollection == null || id.getId() < 0) {
            return null;
        }
        for(PipelineData data: transferCollection){
            if (data.getID().getId() == id.getId()){
                return data;
            }
        }
        return null;
    }

    @Override
    public PipelineData take() {
        PipelineData toTake = null;
        try {
            toTake = transferCollection.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return toTake;
    }

    @Override
    public void put(PipelineData data) {
        transferCollection.add(data);
    }

    public PipelineID getPipelineCollectionId() {
        return pipelineCollectionId;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "\npipelineCollectionId=" + pipelineCollectionId +
                ", \ntransferCollection=" + transferCollection +
                '}';
    }
}

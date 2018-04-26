package org.raf.kids.domaci.transfer;

import org.raf.kids.domaci.vo.PipelineID;

import java.util.ArrayList;
import java.util.List;

public class Collection implements PipelineCollection {

    private PipelineID pipelineCollectionId;
    private List<PipelineData> transferCollection;//TODO BlockingQueue

    public Collection(PipelineID pipelineCollectionId) {
        this.pipelineCollectionId = pipelineCollectionId;
        transferCollection = new ArrayList<>();
    }

    @Override
    public PipelineID getId() {
        return pipelineCollectionId;
    }

    //TODO call is not blocking
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

    //TODO block current thread if collection is empty
    @Override
    public PipelineData take() {
        PipelineData toTake = transferCollection.get(transferCollection.size());
        //transferCollection.remove();
        return toTake;
    }

    @Override
    public void put(PipelineData data) {
        if (transferCollection == null) {
            transferCollection = new ArrayList<>();
        }
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

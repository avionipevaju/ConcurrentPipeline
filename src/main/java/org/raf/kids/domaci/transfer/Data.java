package org.raf.kids.domaci.transfer;

import org.raf.kids.domaci.vo.PipelineID;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Data implements PipelineData {

    private PipelineID pipelineDataID;
    private ConcurrentMap<String, Object> transferData;

    public Data(PipelineID pipelineDataID) {
        this.pipelineDataID = pipelineDataID;
        transferData = new ConcurrentHashMap<>();
    }

    @Override
    public PipelineID getID() {
        return pipelineDataID;
    }

    @Override
    public Object getValue(String key) {
        if (key == null) {
            return null;
        }
        if (transferData == null) {
            transferData = new ConcurrentHashMap<>();
        }
        return  transferData.get(key);
    }

    @Override
    public void setValue(String key, Object value) {
        if (key == null) {
            return;
        }
        if (transferData == null) {
            transferData = new ConcurrentHashMap<>();
        }
        transferData.put(key, value);
    }

    public PipelineID getPipelineDataID() {
        return pipelineDataID;
    }

    @Override
    public String toString() {
        return "Data{" +
                "\npipelineDataID=" + pipelineDataID +
                ", \ntransferData=" + transferData +
                '}';
    }
}

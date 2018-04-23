package org.raf.kids.domaci.transfer;

import org.raf.kids.domaci.vo.PipelineID;

import java.util.HashMap;

public class Data implements PipelineData {

    private PipelineID pipelineDataID;
    private HashMap<String, Object> transferData;

    public Data(PipelineID pipelineDataID) {
        this.pipelineDataID = pipelineDataID;
        transferData = new HashMap<>();
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
            transferData = new HashMap<>();
        }
        return  transferData.get(key);
    }

    @Override
    public void setValue(String key, Object value) {
        if (key == null) {
            return;
        }
        if (transferData == null) {
            transferData = new HashMap<>();
        }
        transferData.put(key, value);
    }

    public PipelineID getPipelineDataID() {
        return pipelineDataID;
    }
}

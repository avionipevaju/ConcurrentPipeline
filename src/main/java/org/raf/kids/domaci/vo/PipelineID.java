package org.raf.kids.domaci.vo;

public class PipelineID implements Comparable<PipelineID>{

    private int dataId;
    private String nodeName;

    public PipelineID(int dataId, String nodeName) {
        this.dataId = dataId;
        this.nodeName = nodeName;
    }

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    @Override
    public int compareTo(PipelineID o) {
        if(o.dataId == dataId && o.nodeName == nodeName) {
            return 1;
        } else
            return 0;
    }

    @Override
    public String toString() {
        return "PipelineID{" +
                "dataId=" + dataId +
                ", nodeName='" + nodeName + '\'' +
                '}';
    }
}

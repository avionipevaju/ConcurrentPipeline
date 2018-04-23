package org.raf.kids.domaci.transfer;

import org.raf.kids.domaci.vo.PipelineID;

public interface PipelineData {

    PipelineID getID();
    Object getValue(String key);
    void setValue(String key, Object value);

}

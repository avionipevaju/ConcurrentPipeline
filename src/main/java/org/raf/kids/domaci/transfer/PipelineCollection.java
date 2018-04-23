package org.raf.kids.domaci.transfer;

import org.raf.kids.domaci.vo.PipelineID;

public interface PipelineCollection {

    PipelineID getId();
    PipelineData peek(PipelineID id);
    PipelineData take(PipelineID id); //TODO should it take id as a parameter
    void put(PipelineData data);

}

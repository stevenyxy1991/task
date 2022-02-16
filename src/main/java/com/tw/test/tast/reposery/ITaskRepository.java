package com.tw.test.tast.reposery;

import com.tw.test.tast.entity.TaskEntityPo;
import com.tw.test.tast.util.OperationType;

public interface ITaskRepository extends BaseRepository<TaskEntityPo> {

    OperationType operationDef();

}

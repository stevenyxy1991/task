package com.tw.test.task.reposery;

import com.tw.test.task.entity.TaskEntityPo;
import com.tw.test.task.util.OperationType;

public interface ITaskRepository extends BaseRepository<TaskEntityPo> {

    OperationType operationDef();

}

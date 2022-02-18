package com.tw.test.task.service;

import com.tw.test.task.entity.TaskEntityPo;
import com.tw.test.task.factory.OperationFactory;
import com.tw.test.task.model.Context;
import com.tw.test.task.model.TaskShareModel;
import com.tw.test.task.model.UserModel;
import com.tw.test.task.util.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements BaseService<TaskEntityPo> {


    @Autowired
    private OperationFactory operationFactory;

    @Autowired
    private Context context;


    public TaskEntityPo save(TaskEntityPo taskEntity) {
        operationFactory.getOperationService(OperationType.getType(context.getContext().get("optionType").toString())).save(taskEntity);
        return taskEntity;

    }

    public TaskEntityPo update(TaskEntityPo taskEntity) {
        operationFactory.getOperationService(OperationType.getType(context.getContext().get("optionType").toString())).update(taskEntity);
        return taskEntity;
    }

    @Override
    public void deleteById(Long id) {
        operationFactory.getOperationService(OperationType.getType(context.getContext().get("optionType").toString())).deleteById(id);
    }

    public TaskEntityPo getById(Long id) {
        return operationFactory.getOperationService(OperationType.getType(context.getContext().get("optionType").toString())).getById(id);
    }

    public List<TaskEntityPo> queryAll() {
        return operationFactory.getOperationService(OperationType.getType(context.getContext().get("optionType").toString())).queryAll();
    }

    public void share(TaskShareModel taskModel){
        operationFactory.getOperationService(OperationType.getType(context.getContext().get("optionType").toString())).share(taskModel);
    }
}

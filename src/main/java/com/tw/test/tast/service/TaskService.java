package com.tw.test.tast.service;

import com.tw.test.tast.entity.TaskEntityPo;
import com.tw.test.tast.factory.OperationFactory;
import com.tw.test.tast.util.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements BaseService<TaskEntityPo> {


    @Autowired
    private OperationFactory operationFactory;

    @Value("${operation.type:JPA}")
    private String operationType;


    @Override
    public TaskEntityPo save(TaskEntityPo taskEntity) {
        operationFactory.getOperationService(OperationType.getType(operationType)).save(taskEntity);
        return taskEntity;

    }

    @Override
    public TaskEntityPo update(TaskEntityPo taskEntity) {
        operationFactory.getOperationService(OperationType.getType(operationType)).update(taskEntity);
        return null;

    }

    @Override
    public TaskEntityPo deleteById(Long id) {
        operationFactory.getOperationService(OperationType.getType(operationType)).deleteById(id);
        return null;
    }

    @Override
    public TaskEntityPo getById(Long id) {
        return operationFactory.getOperationService(OperationType.getType(operationType)).getById(id);
    }

    @Override
    public List<TaskEntityPo> queryAll() {
        return operationFactory.getOperationService(OperationType.getType(operationType)).queryAll();
    }

    @Override
    public List<TaskEntityPo> queryAll(int start, int pageSize) {
        return operationFactory.getOperationService(OperationType.getType(operationType)).queryAll(start,pageSize);
    }

}

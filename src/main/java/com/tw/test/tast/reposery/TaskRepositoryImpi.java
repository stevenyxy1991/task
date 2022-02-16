package com.tw.test.tast.reposery;

import com.tw.test.tast.dao.ITaskDao;
import com.tw.test.tast.entity.TaskEntityPo;
import com.tw.test.tast.util.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TaskRepositoryImpi implements ITaskRepository {

    @Autowired
    private ITaskDao taskDao;

    @Override
    public OperationType operationDef(){
        return OperationType.JPA;
    }
    @Override
    public void update(TaskEntityPo taskEntity) {
        taskDao.save(taskEntity);
    }

    @Override
    public void save(TaskEntityPo taskEntityPo) {
        taskDao.save(taskEntityPo);
    }

    @Override
    public void deleteById(Long id) {
        taskDao.deleteById(id);
    }

    @Override
    public TaskEntityPo getById(Long id) {
        return taskDao.getById(id);
    }

    @Override
    public List<TaskEntityPo> queryAll() {
        return taskDao.findAll();
    }

    @Override
    public List<TaskEntityPo> queryAll(int start, int pageSize) {
        Pageable pageable = PageRequest.of(start, pageSize);
        return taskDao.findAll(pageable).get().collect(Collectors.toList());
    }

    protected void deleteByUserId(Long id){
        taskDao.deleteAllByUserId(String.valueOf(id));
    }
}

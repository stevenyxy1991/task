package com.tw.test.task.reposery;

import com.tw.test.task.dao.ITaskDao;
import com.tw.test.task.entity.TaskEntityPo;
import com.tw.test.task.util.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TaskRepositoryImpl implements ITaskRepository {

    private static final String SEMPOL = "%";

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

    public void saveAll(List<TaskEntityPo> taskEntityPos) {
        taskDao.saveAll(taskEntityPos);
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
        Pageable pageable = PageRequest.of(start - 1, pageSize);
        return taskDao.findAll(pageable).getContent();
    }

    protected void deleteByUserId(Long id){
        StringBuilder sb = new StringBuilder(SEMPOL);
        taskDao.deleteByRelation(sb.append(id).append(SEMPOL).toString());
    }

    protected List<TaskEntityPo> findAllByRelation(Long id){
        StringBuilder sb = new StringBuilder(SEMPOL);
        return taskDao.findAllByRelationLike(sb.append(id).append(SEMPOL).toString());
    }
}

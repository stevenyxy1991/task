package com.tw.test.task.reposery;

import com.tw.test.task.dao.ITaskDao;
import com.tw.test.task.entity.TaskEntityPo;
import com.tw.test.task.model.Context;
import com.tw.test.task.model.TaskShareModel;
import com.tw.test.task.model.UserModel;
import com.tw.test.task.util.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TaskRepositoryImpl implements ITaskRepository {

    @Autowired
    private ITaskDao taskDao;

    @Autowired
    private Context context;

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
        Optional pageable = Optional.ofNullable(context.getContext().get("pageable"));
        if (pageable.isPresent()) {
            return taskDao.findAll((Pageable) pageable.get()).getContent();

        }
        return taskDao.findAll();
    }

    @Override
    public void share(TaskShareModel taskShareModel){
        Long sharedId = taskShareModel.getShareId();
        Long userId = taskShareModel.getUserEntity().getId();
        taskShareModel.getTaskEntitys().stream().forEach(t -> {
            StringBuilder sb = new StringBuilder(SYMBOL);
            sb.append(sharedId).append("#true").append(SYMBOL).append(userId);
            t.setRelation(sb.toString());
        });
        taskDao.saveAll(taskShareModel.getTaskEntitys());
    }

    protected void deleteByUserId(Long id){
        StringBuilder sb = new StringBuilder(PERCENT);
        taskDao.deleteByRelation(sb.append(id).append(PERCENT).toString());
    }

    protected List<TaskEntityPo> findAllByRelation(Long id){
        StringBuilder sb = new StringBuilder(PERCENT);
        return taskDao.findAllByRelationLike(sb.append(id).append(PERCENT).toString());
    }
}

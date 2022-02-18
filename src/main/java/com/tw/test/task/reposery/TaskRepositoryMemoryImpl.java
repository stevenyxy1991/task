package com.tw.test.task.reposery;

import com.tw.test.task.entity.ContectEntity;
import com.tw.test.task.entity.TaskEntityPo;
import com.tw.test.task.entity.UserEntityPo;
import com.tw.test.task.model.TaskShareModel;
import com.tw.test.task.util.OperationType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class TaskRepositoryMemoryImpl implements ITaskRepository {

    @Override
    public OperationType operationDef(){
        return OperationType.MEMORY;
    }

    private final List<UserEntityPo> userEntities;
    private final List<TaskEntityPo> taskEntities;
    private final List<ContectEntity> contectEntities;

    public TaskRepositoryMemoryImpl(List<UserEntityPo> userEntities, List<TaskEntityPo> taskEntities, List<ContectEntity> contectEntities) {
        this.userEntities = userEntities;
        this.taskEntities = taskEntities;
        this.contectEntities = contectEntities;
    }
    
    @Override
    public void save(TaskEntityPo taskEntity) {
        taskEntities.add(taskEntity);
    }

    @Override
    public void update(TaskEntityPo taskEntity) {
        taskEntities.stream().filter(it -> it.getId().equals(taskEntity.getId())).findFirst().ifPresent(it -> BeanUtils.copyProperties(taskEntity, it));
    }

    @Override
    public void deleteById(Long id) {
        Iterator iterator = taskEntities.iterator();
        while (iterator.hasNext()){
            TaskEntityPo taskEntityPo = (TaskEntityPo) iterator.next();
            if (id == taskEntityPo.getId()){
                iterator.remove();
            }
        }
    }

    @Override
    public TaskEntityPo getById(Long id) {
        Map<Long,TaskEntityPo> map = taskEntities.stream().collect(Collectors.toMap(TaskEntityPo::getId, TaskEntityPo -> TaskEntityPo));
        return map.get(id);
    }

    @Override
    public List<TaskEntityPo> queryAll() {
        return taskEntities;
    }

    @Override
    public void share(TaskShareModel taskShareModel){
        List<TaskEntityPo> taskEntityList = taskShareModel.getTaskEntitys();

        if (Optional.ofNullable(taskEntityList).isPresent() || taskShareModel.getShareId() != null){
            contectEntities.stream().filter(c -> c.getIsShare() || c.getUserId() == taskShareModel.getUserEntity().getId()).forEach(con -> {
                taskEntityList.stream().filter(t -> t.getId() == con.getTaskId()).forEach(task ->{
                    contectEntities.add(new ContectEntity(taskShareModel.getShareId(),task.getId(),true, taskShareModel.getUserEntity().getId(), LocalDateTime.now()));
                });
            });
        }
    }

}

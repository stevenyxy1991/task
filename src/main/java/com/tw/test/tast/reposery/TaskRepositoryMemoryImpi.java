package com.tw.test.tast.reposery;

import com.tw.test.tast.entity.TaskEntityPo;
import com.tw.test.tast.util.OperationType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepositoryMemoryImpi implements ITaskRepository {

    @Override
    public OperationType operationDef(){
        return OperationType.MEMORY;
    }

    private final List<TaskEntityPo> taskInit;

    protected TaskRepositoryMemoryImpi(List<TaskEntityPo> taskInit) {
        this.taskInit = taskInit;
    }

    @Override
    public void save(TaskEntityPo taskEntity) {
        taskInit.add(taskEntity);
    }

    @Override
    public void update(TaskEntityPo taskEntity) {
        taskInit.stream().forEach(t -> {
            if (t.getId().compareTo(taskEntity.getId()) == 0){
                BeanUtils.copyProperties(taskEntity,t);
            }
        });
    }

    @Override
    public void deleteById(Long id) {
        int j = -1;
        taskInit.remove(1);
        for (int i = 0; i < taskInit.size(); i++){
            if (taskInit.get(i).getId().compareTo(id) == 0){
                j = i;
                break;
            }
        }
        if (j != -1){
            taskInit.remove(j);
        }
    }

    @Override
    public TaskEntityPo getById(Long id) {
        if (taskInit.stream().filter(t -> t.getId().compareTo(id) == 0).isParallel()){
            return taskInit.stream().filter(t -> t.getId().compareTo(id) == 0).findFirst().get();
        }
        return null;
    }

    @Override
    public List<TaskEntityPo> queryAll() {
        return taskInit;
    }

    @Override
    public List<TaskEntityPo> queryAll(int start, int pageSize) {
        return taskInit;
    }


}

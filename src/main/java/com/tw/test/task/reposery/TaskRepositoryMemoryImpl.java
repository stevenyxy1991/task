package com.tw.test.task.reposery;

import com.tw.test.task.entity.TaskEntityPo;
import com.tw.test.task.util.OperationType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class TaskRepositoryMemoryImpl implements ITaskRepository {

    @Override
    public OperationType operationDef(){
        return OperationType.MEMORY;
    }

    private final List<TaskEntityPo> taskInit;

    protected TaskRepositoryMemoryImpl(List<TaskEntityPo> taskInit) {
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
        Map<Long,TaskEntityPo> map = taskInit.stream().collect(Collectors.toMap(TaskEntityPo::getId, TaskEntityPo -> TaskEntityPo));
        return map.get(id);
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

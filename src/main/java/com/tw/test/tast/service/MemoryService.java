//package com.tw.test.tast.service;
//
//import com.tw.test.tast.entity.TaskEntity;
//import org.springframework.beans.BeanUtils;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class MemoryService implements BaseService<TaskEntity>{
//
//    private final List<TaskEntity> taskInit;
//
//
//    public MemoryService(List<TaskEntity> taskInit) {
//        this.taskInit = taskInit;
//    }
//
//    @Override
//    public TaskEntity save(TaskEntity taskEntity) {
//        taskInit.add(taskEntity);
//        return taskEntity;
//    }
//
//    @Override
//    public TaskEntity update(TaskEntity taskEntity) {
//        taskInit.stream().forEach(t -> {
//            if (t.getId().compareTo(taskEntity.getId()) == 0){
//                BeanUtils.copyProperties(taskEntity,t);
//            }
//        });
//        return taskEntity;
//    }
//
//    @Override
//    public TaskEntity deleteById(Long id) {
//        int j = -1;
//        taskInit.remove(1);
//        for (int i = 0; i < taskInit.size(); i++){
//            if (taskInit.get(i).getId().compareTo(id) == 0){
//                j = i;
//                break;
//            }
//        }
//        if (j != -1){
//            taskInit.remove(j);
//        }
//        return null;
//    }
//
//    @Override
//    public TaskEntity getById(Long id) {
//        if (taskInit.stream().filter(t -> t.getId().compareTo(id) == 0).isParallel()){
//            return taskInit.stream().filter(t -> t.getId().compareTo(id) == 0).findFirst().get();
//        }
//        return null;
//    }
//
//    @Override
//    public TaskEntity getByName(String name) {
//        return null;
//    }
//
//    @Override
//    public List<TaskEntity> queryAll() {
//        return taskInit;
//    }
//}

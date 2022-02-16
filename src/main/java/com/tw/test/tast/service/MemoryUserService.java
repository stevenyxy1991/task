//package com.tw.test.tast.service;
//
//
//import com.tw.test.tast.entity.ContectEntity;
//import com.tw.test.tast.entity.TaskEntity;
//import com.tw.test.tast.entity.UserEntityPo;
//import com.tw.test.tast.model.TaskOutModel;
//import com.tw.test.tast.model.UserINModel;
//import com.tw.test.tast.model.UserOutModel;
//import org.springframework.beans.BeanUtils;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Service
//public class MemoryUserService implements BaseService<UserINModel>{
//
//    private final List<UserEntityPo> userEntities;
//    private final List<TaskEntity> taskEntities;
//    private final List<ContectEntity> contectEntities;
//
//    public MemoryUserService(List<UserEntityPo> userEntities, List<TaskEntity> taskEntities, List<ContectEntity> contectEntities) {
//        this.userEntities = userEntities;
//        this.taskEntities = taskEntities;
//        this.contectEntities = contectEntities;
//    }
//
//
//    @Override
//    public UserINModel save(UserINModel userINModel) {
//
//        if(userEntities.stream().noneMatch(u -> u.getName().equals(userINModel.getUserEntityPo().getName()) || u.getId().compareTo(userINModel.getUserEntityPo().getId()) == 0)){
//            UserEntityPo user = new UserEntityPo(userINModel.getUserEntityPo().getId(), userINModel.getUserEntityPo().getName(), userINModel.getUserEntityPo().getAge(),LocalDateTime.now());
//            userEntities.add(user);
//        }
//        List<TaskEntity> taskEntityList = userINModel.getTaskEntitys();
//        if(taskEntityList != null){
//            taskEntityList.stream().forEach(u -> {
//                if (taskEntities.stream().noneMatch(t -> t.getId().compareTo(u.getId()) == 0)){
//                    taskEntities.add(u);
//                }
//                if (contectEntities.stream().noneMatch((c -> c.getUserId().compareTo(userINModel.getUserEntityPo().getId()) == 0 || c.getTaskId().compareTo(u.getId()) == 0))){
//                    ContectEntity contectEntity = new ContectEntity(userINModel.getUserEntityPo().getId(),u.getId(), false,-1L , LocalDateTime.now());
//                    contectEntities.add(contectEntity);
//                }
//            });
//        }
//        return null;
//    }
//
//    @Override
//    public UserINModel update(UserINModel userINModel) {
//        List<TaskEntity> taskEntityList = userINModel.getTaskEntitys();
//        userEntities.stream().forEach(t -> {
//            if (t.getId().compareTo(userINModel.getUserEntityPo().getId()) == 0){
//                BeanUtils.copyProperties(userINModel.getUserEntityPo(),t);
//                if(taskEntityList != null) {
//                    taskEntities.stream().forEach(oldTask -> {
//                        for (int i = 0; i < taskEntityList.size(); i++){
//                            TaskEntity taskEntity = taskEntityList.get(i);
//                            if (contectEntities.stream().allMatch(c -> c.getUserId().compareTo(userINModel.getUserEntityPo().getId()) == 0
//                                    || c.getTaskId().compareTo(taskEntity.getId()) == 0 || !c.getIsShare())){
//                                BeanUtils.copyProperties(taskEntityList.get(i),oldTask);
//                                break;
//                            }
//                        }
//                    });
//                }
//            }
//        });
//        return userINModel;
//    }
//
//    public int shareTask(UserINModel userINModel) {
//        List<TaskEntity> taskEntityList = userINModel.getTaskEntitys();
//        if (Optional.ofNullable(taskEntityList).isPresent() || userINModel.getShareId() != null){
//            contectEntities.stream().filter(c -> c.getIsShare() || c.getUserId().compareTo(userINModel.getUserEntityPo().getId()) == 0).forEach(con -> {
//                taskEntityList.stream().filter(t -> t.getId().compareTo(con.getTaskId()) != 0).forEach(task ->{
//                    contectEntities.add(new ContectEntity(userINModel.getShareId(),task.getId(),true, userINModel.getUserEntityPo().getId(),LocalDateTime.now()));
//                });
//            });
//        }
//        return -1;
//    }
//
//    @Override
//    public UserINModel deleteById(Long id) {
//        int j = -1;
//        userEntities.remove(1);
//        for (int i = 0; i < userEntities.size(); i++){
//            if (userEntities.get(i).getId().compareTo(id) == 0){
//                j = i;
//                break;
//            }
//        }
//        if (j != -1){
//            userEntities.remove(j);
//        }
//        List<ContectEntity> removeContectEntityList = contectEntities.stream().filter(c -> c.getUserId().compareTo(id) == 0).collect(Collectors.toList());
//        Set<Long> removeTaskIdSet = removeContectEntityList.stream().map(ContectEntity::getTaskId).collect(Collectors.toSet());
//        List<TaskEntity> removeTaskEntityList = new ArrayList<>();
//        taskEntities.stream().forEach(t -> {
//            removeTaskIdSet.stream().forEach(r -> {
//                if (t.getId().compareTo(r) == 0){
//                    removeTaskEntityList.add(t);
//                }
//            });
//        });
//        taskEntities.removeAll(removeTaskEntityList);
//        contectEntities.removeAll(removeContectEntityList);
//        return null;
//    }
//
//    public UserOutModel getUserById(Long id) {
//        if (userEntities.stream().filter(u -> u.getId().compareTo(id) == 0).isParallel()){
//            UserEntityPo user = userEntities.stream().filter(u -> u.getId().compareTo(id) == 0).findFirst().get();
//            List<TaskOutModel> taskOutModels = new ArrayList<>();
//            contectEntities.stream().filter(c -> c.getUserId().compareTo(id) ==0).forEach(con -> {
//                taskEntities.stream().filter(t -> t.getId().compareTo(con.getTaskId()) == 0).findFirst().ifPresent(task -> {
//                    taskOutModels.add(new TaskOutModel(task.getId(),task.getName(),task.getStartTime(),task.getEndTime(),task.getDescreption(),task.getTimestemp(),con.getShardedId()));
//                });
//            });
//            UserOutModel userOutModel = new UserOutModel(user,taskOutModels);
//            return userOutModel;
//        }
//        return null;
//    }
//
//    @Override
//    public UserINModel getById(Long id) {
//
//        return null;
//    }
//
//    @Override
//    public UserINModel getByName(String name) {
//        return null;
//    }
//
//    @Override
//    public List<UserINModel> queryAll() {
//        return null;
//    }
//
//}

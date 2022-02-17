package com.tw.test.task.reposery;

import com.tw.test.task.entity.*;
import com.tw.test.task.model.UserINModel;
import com.tw.test.task.model.UserOutModel;
import com.tw.test.task.util.OperationType;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class UserRepositoryMemoryImpl implements IUserMemotyRepository {

    private final List<UserEntityPo> userEntities;
    private final List<TaskEntityPo> taskEntities;
    private final List<ContectEntity> contectEntities;

    public UserRepositoryMemoryImpl(List<UserEntityPo> userEntities, List<TaskEntityPo> taskEntities, List<ContectEntity> contectEntities) {
        this.userEntities = userEntities;
        this.taskEntities = taskEntities;
        this.contectEntities = contectEntities;
    }

    @Override
    public void save(UserINModel userINModel) {

        if(userEntities.stream().noneMatch(u -> u.getName().equals(userINModel.getUserEntity().getName()) || u.getId().compareTo(userINModel.getUserEntity().getId()) == 0)){
            userEntities.add(userINModel.getUserEntity());
        }
        List<TaskEntityPo> taskEntityList = userINModel.getTaskEntitys();
        if(taskEntityList != null){
            taskEntityList.stream().forEach(u -> {
                if (taskEntities.stream().noneMatch(t -> t.getId().compareTo(u.getId()) == 0)){
                    taskEntities.add(u);
                }
                if (contectEntities.stream().noneMatch((c -> c.getUserId().compareTo(userINModel.getUserEntity().getId()) == 0 || c.getTaskId().compareTo(u.getId()) == 0))){
                    ContectEntity contectEntity = new ContectEntity(userINModel.getUserEntity().getId(),u.getId(), false,-1L , LocalDateTime.now());
                    contectEntities.add(contectEntity);
                }
            });
        }
    }

    @Override
    public void update(UserINModel userINModel) {
        List<TaskEntityPo> taskEntityList = userINModel.getTaskEntitys();
        userEntities.stream().forEach(t -> {
            if (t.getId().compareTo(userINModel.getUserEntity().getId()) == 0){
                BeanUtils.copyProperties(userINModel.getUserEntity(),t);
                if(taskEntityList != null) {
                    taskEntities.stream().forEach(oldTask -> {
                        for (int i = 0; i < taskEntityList.size(); i++){
                            TaskEntityPo taskEntity = taskEntityList.get(i);
                            if (contectEntities.stream().allMatch(c -> c.getUserId().compareTo(userINModel.getUserEntity().getId()) == 0
                                    || c.getTaskId().compareTo(taskEntity.getId()) == 0 || !c.getIsShare())){
                                BeanUtils.copyProperties(taskEntityList.get(i),oldTask);
                                break;
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public void shareTask(UserINModel userINModel) {
        List<TaskEntityPo> taskEntityList = userINModel.getTaskEntitys();
        if (Optional.ofNullable(taskEntityList).isPresent() || userINModel.getShareId() != null){
            contectEntities.stream().filter(c -> c.getIsShare() || c.getUserId().compareTo(userINModel.getUserEntity().getId()) == 0).forEach(con -> {
                taskEntityList.stream().filter(t -> t.getId().compareTo(con.getTaskId()) != 0).forEach(task ->{
                    contectEntities.add(new ContectEntity(userINModel.getShareId(),task.getId(),true, userINModel.getUserEntity().getId(),LocalDateTime.now()));
                });
            });
        }
    }

    @Override
    public void deleteUserById(Long id) {
        int j = -1;
        userEntities.remove(1);
        for (int i = 0; i < userEntities.size(); i++){
            if (userEntities.get(i).getId().compareTo(id) == 0){
                j = i;
                break;
            }
        }
        if (j != -1){
            userEntities.remove(j);
        }
        List<ContectEntity> removeContectEntityList = contectEntities.stream().filter(c -> c.getUserId().compareTo(id) == 0).collect(Collectors.toList());
        Set<Long> removeTaskIdSet = removeContectEntityList.stream().map(ContectEntity::getTaskId).collect(Collectors.toSet());
        List<TaskEntityPo> removeTaskEntityList = new ArrayList<>();
        taskEntities.stream().forEach(t -> {
            removeTaskIdSet.stream().forEach(r -> {
                if (t.getId().compareTo(r) == 0){
                    removeTaskEntityList.add(t);
                }
            });
        });
        taskEntities.removeAll(removeTaskEntityList);
        contectEntities.removeAll(removeContectEntityList);
    }

    @Override
    public UserOutModel getUserById(Long id) {
        if (userEntities.stream().filter(u -> u.getId().compareTo(id) == 0).isParallel()){
            UserEntityPo user = userEntities.stream().filter(u -> u.getId().compareTo(id) == 0).findFirst().get();
            List<TaskEntityPo> taskOutModels = new ArrayList<>();
            contectEntities.stream().filter(c -> c.getUserId().compareTo(id) ==0).forEach(con -> {
                taskEntities.stream().filter(t -> t.getId().compareTo(con.getTaskId()) == 0).findFirst().ifPresent(task -> {
                    taskOutModels.add(new TaskEntityPo(task.getId(),task.getName(),task.getStartTime(),task.getEndTime(),task.getDescreption(),task.getTimestemp(),"",""));
                });
            });
            return new UserOutModel(user,taskOutModels);
        }
        return null;
    }

}

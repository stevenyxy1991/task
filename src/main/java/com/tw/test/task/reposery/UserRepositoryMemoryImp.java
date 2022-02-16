package com.tw.test.task.reposery;

import com.tw.test.task.entity.ContectEntity;
import com.tw.test.task.entity.TaskEntity;
import com.tw.test.task.entity.UserEntity;
import com.tw.test.task.model.TaskOutModel;
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

public class UserRepositoryMemoryImp implements IUserMemotyRepository {

    private final List<UserEntity> userEntities;
    private final List<TaskEntity> taskEntities;
    private final List<ContectEntity> contectEntities;

    public UserRepositoryMemoryImp(List<UserEntity> userEntities, List<TaskEntity> taskEntities, List<ContectEntity> contectEntities) {
        this.userEntities = userEntities;
        this.taskEntities = taskEntities;
        this.contectEntities = contectEntities;
    }

    @Override
    public void save(UserINModel userINModel) {

        if(userEntities.stream().noneMatch(u -> u.getName().equals(userINModel.getUserEntity().getName()) || u.getId().compareTo(userINModel.getUserEntity().getId()) == 0)){
            UserEntity user = new UserEntity(userINModel.getUserEntity().getId(), userINModel.getUserEntity().getName(), userINModel.getUserEntity().getAge(), LocalDateTime.now());
            userEntities.add(user);
        }
        List<TaskEntity> taskEntityList = userINModel.getTaskEntitys();
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

    public void update(UserINModel userINModel) {
        List<TaskEntity> taskEntityList = userINModel.getTaskEntitys();
        userEntities.stream().forEach(t -> {
            if (t.getId().compareTo(userINModel.getUserEntity().getId()) == 0){
                BeanUtils.copyProperties(userINModel.getUserEntity(),t);
                if(taskEntityList != null) {
                    taskEntities.stream().forEach(oldTask -> {
                        for (int i = 0; i < taskEntityList.size(); i++){
                            TaskEntity taskEntity = taskEntityList.get(i);
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


    public void shareTask(UserINModel userINModel) {
        List<TaskEntity> taskEntityList = userINModel.getTaskEntitys();
        if (Optional.ofNullable(taskEntityList).isPresent() || userINModel.getShareId() != null){
            contectEntities.stream().filter(c -> c.getIsShare() || c.getUserId().compareTo(userINModel.getUserEntity().getId()) == 0).forEach(con -> {
                taskEntityList.stream().filter(t -> t.getId().compareTo(con.getTaskId()) != 0).forEach(task ->{
                    contectEntities.add(new ContectEntity(userINModel.getShareId(),task.getId(),true, userINModel.getUserEntity().getId(),LocalDateTime.now()));
                });
            });
        }
    }


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
        List<TaskEntity> removeTaskEntityList = new ArrayList<>();
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

    public UserOutModel getUserById(Long id) {
        if (userEntities.stream().filter(u -> u.getId().compareTo(id) == 0).isParallel()){
            UserEntity user = userEntities.stream().filter(u -> u.getId().compareTo(id) == 0).findFirst().get();
            List<TaskOutModel> taskOutModels = new ArrayList<>();
            contectEntities.stream().filter(c -> c.getUserId().compareTo(id) ==0).forEach(con -> {
                taskEntities.stream().filter(t -> t.getId().compareTo(con.getTaskId()) == 0).findFirst().ifPresent(task -> {
                    taskOutModels.add(new TaskOutModel(task.getId(),task.getName(),task.getStartTime(),task.getEndTime(),task.getDescreption(),task.getTimestemp(),con.getShardedId()));
                });
            });
            UserOutModel userOutModel = new UserOutModel(user,taskOutModels);
            return userOutModel;
        }
        return null;
    }

    @Override
    public UserINModel getById(Long id) {
        return null;
    }

    @Override
    public List<UserINModel> queryAll() {
        return null;
    }

    @Override
    public List<UserINModel> queryAll(int start, int pageSize) {
        return null;
    }

    @Override
    public OperationType operationDef() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}

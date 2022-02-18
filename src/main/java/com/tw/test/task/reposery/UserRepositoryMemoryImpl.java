package com.tw.test.task.reposery;

import com.tw.test.task.entity.*;
import com.tw.test.task.model.UserModel;
import com.tw.test.task.model.UserOutModel;
import com.tw.test.task.util.OperationType;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class UserRepositoryMemoryImpl implements IUserRepository {

    private final List<UserEntityPo> userEntities;
    private final List<TaskEntityPo> taskEntities;
    private final List<ContectEntity> contectEntities;

    public UserRepositoryMemoryImpl(List<UserEntityPo> userEntities, List<TaskEntityPo> taskEntities, List<ContectEntity> contectEntities) {
        this.userEntities = userEntities;
        this.taskEntities = taskEntities;
        this.contectEntities = contectEntities;
    }

    @Override
    public OperationType operationDef() {
        return OperationType.MEMORY;
    }

    @Override
    public void deleteById(Long id) {
        //delete user
        Iterator userItor = userEntities.iterator();
        while (userItor.hasNext()) {
            UserEntityPo userEntityPo = (UserEntityPo) userItor.next();
            if (id == userEntityPo.getId()) {
                userItor.remove();
            }
        }
        List<ContectEntity> removeContectEntityList = contectEntities.stream().filter(c -> c.getUserId() == id).collect(Collectors.toList());
        Set<Long> removeTaskIdSet = removeContectEntityList.stream().map(ContectEntity::getTaskId).collect(Collectors.toSet());
        List<TaskEntityPo> removeTaskEntityList = new ArrayList<>();
        taskEntities.forEach(t -> {
            removeTaskIdSet.forEach(r -> {
                if (t.getId() == r) {
                    removeTaskEntityList.add(t);
                }
            });
        });
        taskEntities.removeAll(removeTaskEntityList);
        contectEntities.removeAll(removeContectEntityList);
    }

    @Override
    public UserOutModel getByIdInfo(Long id) {
        Optional users = Optional.ofNullable(userEntities.stream().filter(u -> u.getId() == id));
        if (users.isPresent()) {
            UserEntityPo user = (UserEntityPo) users.stream().findFirst().get();
            List<TaskEntityPo> taskOutModels = new ArrayList<>();
            contectEntities.stream().filter(c -> c.getUserId() == id).forEach(con -> {
                taskEntities.stream().filter(t -> t.getId() == con.getTaskId()).findFirst().ifPresent(task -> {
                    taskOutModels.add(new TaskEntityPo(task.getId(), task.getName(), task.getStartTime(), task.getEndTime(), task.getDescreption(), task.getTimestemp(), "", ""));
                });
            });
            return new UserOutModel(user, taskOutModels);
        }
        return null;
    }

    @Override
    public void save(UserModel userModel) {

        if (userEntities.stream().noneMatch(u -> u.getName().equals(userModel.getUserEntity().getName()) || u.getId() == userModel.getUserEntity().getId())) {
            userEntities.add(userModel.getUserEntity());
        }
        List<TaskEntityPo> taskEntityList = userModel.getTaskEntitys();
        if (taskEntityList != null) {
            taskEntityList.forEach(taskNew -> {
                if (taskEntities.stream().noneMatch(taskOld -> taskOld.getId() == taskNew.getId())) {
                    taskEntities.add(taskNew);
                }
                if (contectEntities.stream().noneMatch((c -> c.getUserId() == userModel.getUserEntity().getId() || c.getTaskId() == taskNew.getId()))) {
                    ContectEntity contectEntity = new ContectEntity(userModel.getUserEntity().getId(), taskNew.getId(), false, -1L, LocalDateTime.now());
                    contectEntities.add(contectEntity);
                }
            });
        }
    }

    @Override
    public void update(UserModel userModel) {
        List<TaskEntityPo> taskEntityList = userModel.getTaskEntitys();
        userEntities.forEach(t -> {
            if (t.getId() == userModel.getUserEntity().getId()) {
                BeanUtils.copyProperties(userModel.getUserEntity(), t);
                if (taskEntityList != null) {
                    taskEntities.forEach(oldTask -> {
                        for (int i = 0; i < taskEntityList.size(); i++) {
                            TaskEntityPo taskEntity = taskEntityList.get(i);
                            if (contectEntities.stream().allMatch(c -> c.getUserId() == userModel.getUserEntity().getId()
                                    || c.getTaskId() == taskEntity.getId() || !c.getIsShare())) {
                                BeanUtils.copyProperties(taskEntityList.get(i), oldTask);
                                break;
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public List<UserEntityPo> queryAll() {
        return userEntities;
    }
}

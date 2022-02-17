package com.tw.test.task.reposery;

import com.tw.test.task.dao.IUserDao;
import com.tw.test.task.entity.UserEntityPo;
import com.tw.test.task.model.UserINModel;
import com.tw.test.task.model.UserModel;
import com.tw.test.task.model.UserOutModel;
import com.tw.test.task.util.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements IUserRepository {

    private static final String SYMBOL ="#";

    @Autowired
    private TaskRepositoryImpl taskRepositoryImpl;

    @Autowired
    private IUserDao userDao;

    @Override
    public OperationType operationDef(){
        return OperationType.JPA;
    }

    @Override
    public void deleteUserById(Long id) {
        userDao.deleteById(id);
        taskRepositoryImpl.deleteByUserId(id);
    }

    @Override
    public void save(UserEntityPo userEntityPo) {
        userDao.save(userEntityPo);
    }

    @Override
    public void update(UserEntityPo userEntityPo) {
        userDao.save(userEntityPo);
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public UserEntityPo getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public List<UserEntityPo> queryAll() {
        return userDao.findAll();
    }

    @Override
    public List<UserEntityPo> queryAll(int start, int pageSize) {
        Pageable pageable = PageRequest.of(start, pageSize);
        return userDao.findAll(pageable).get().collect(Collectors.toList());
    }

    public void saveUserTask(UserModel userModel){
        userDao.save(userModel.getUserEntity());
        if (null != userModel.getTaskEntitys()) {
            userModel.getTaskEntitys().stream().forEach(t -> {
                StringBuilder sb = new StringBuilder(SYMBOL);
                sb.append(userModel.getUserEntity().getId()).append("#false");
                t.setRelation(sb.toString());
            });
            taskRepositoryImpl.saveAll(userModel.getTaskEntitys());
        }
    }

    public void share(UserModel userModel){
        Long sharedId = userModel.getShareId();
        Long userId = userModel.getUserEntity().getId();
        userModel.getTaskEntitys().stream().forEach(t -> {
            StringBuilder sb = new StringBuilder(SYMBOL);
            sb.append(sharedId).append("#true").append(SYMBOL).append(userId);
            t.setRelation(sb.toString());
        });
        taskRepositoryImpl.saveAll(userModel.getTaskEntitys());
    }

    public UserOutModel getByIdInfo(Long id) {
        UserOutModel userOutModel = new UserOutModel();
        userOutModel.setUserEntity(userDao.getById(id));
        userOutModel.setTaskEntitys(taskRepositoryImpl.findAllByRelation(id));
        return userOutModel;
    }
}

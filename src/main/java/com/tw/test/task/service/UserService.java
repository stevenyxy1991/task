package com.tw.test.task.service;

import com.tw.test.task.entity.UserEntityPo;
import com.tw.test.task.model.UserINModel;
import com.tw.test.task.model.UserModel;
import com.tw.test.task.model.UserOutModel;
import com.tw.test.task.reposery.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements BaseService<UserEntityPo> {


    @Autowired
    private UserRepositoryImpl operationFactory;


    @Override
    public UserEntityPo save(UserEntityPo userEntity) {
        operationFactory.save(userEntity);
        return userEntity;
    }

    @Override
    public UserEntityPo update(UserEntityPo userEntity) {
        operationFactory.save(userEntity);
        return userEntity;
    }

    @Override
    public void deleteById(Long id) {
        operationFactory.deleteUserById(id);
    }

    @Override
    public UserEntityPo getById(Long id) {
        return operationFactory.getById(id);
    }

    @Override
    public List<UserEntityPo> queryAll(int page, int pageSize) {
        return operationFactory.queryAll(page,pageSize);
    }

    @Override
    public List<UserEntityPo> queryAll() {
        return operationFactory.queryAll();
    }

    public void share(UserModel userModel){
        operationFactory.share(userModel);
    }

    public UserModel saveUserTask(UserModel userModel) {
        operationFactory.saveUserTask(userModel);
        return userModel;
    }

    public UserOutModel getByIdInfo(Long id) {
        return operationFactory.getByIdInfo(id);
    }
}

package com.tw.test.task.service;

import com.tw.test.task.entity.UserEntityPo;
import com.tw.test.task.factory.OperationFactory;
import com.tw.test.task.model.Context;
import com.tw.test.task.model.UserModel;
import com.tw.test.task.model.UserOutModel;
import com.tw.test.task.reposery.UserRepositoryImpl;
import com.tw.test.task.util.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements BaseService<UserEntityPo> {


    @Autowired
    private OperationFactory operationFactory;

    @Autowired
    private Context context;


    public UserEntityPo save(UserModel userEntity) {
        operationFactory.getOperationUserService(OperationType.getType(context.getContext().get("optionType").toString())).save(userEntity);
        return userEntity.getUserEntity();
    }

    public UserEntityPo update(UserModel userEntity) {
        operationFactory.getOperationUserService(OperationType.getType(context.getContext().get("optionType").toString())).save(userEntity);
        return userEntity.getUserEntity();
    }

    @Override
    public void deleteById(Long id) {
        operationFactory.getOperationUserService(OperationType.getType(context.getContext().get("optionType").toString())).deleteById(id);
    }

    public UserOutModel getById(Long id) {
        return operationFactory.getOperationUserService(OperationType.getType(context.getContext().get("optionType").toString())).getByIdInfo(id);
    }

    public List<UserEntityPo> queryAll() {
        return operationFactory.getOperationUserService(OperationType.getType(context.getContext().get("optionType").toString())).queryAll();
    }

    public UserModel saveUserTask(UserModel userModel) {
        operationFactory.getOperationUserService(OperationType.getType(context.getContext().get("optionType").toString())).save(userModel);
        return userModel;
    }
}

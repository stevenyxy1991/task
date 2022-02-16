package com.tw.test.tast.service;

import com.tw.test.tast.entity.BaseEntity;
import com.tw.test.tast.entity.UserEntityPo;
import com.tw.test.tast.factory.OperationFactory;
import com.tw.test.tast.reposery.UserRepositoryImpi;
import com.tw.test.tast.util.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements BaseService<UserEntityPo> {


    @Autowired
    private UserRepositoryImpi operationFactory;

    @Value("${operation.type:JPA}")
    private String operationType;

    @Override
    public UserEntityPo save(UserEntityPo userEntity) {
        operationFactory.save(userEntity);
        return null;
    }

    @Override
    public UserEntityPo update(UserEntityPo userEntity) {
        operationFactory.save(userEntity);
        return null;
    }

    @Override
    public UserEntityPo deleteById(Long id) {
        operationFactory.deleteUserById(id);
        return null;
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

    public void deleteByUserId(Long id){
        operationFactory.deleteUserById(id);
    }


}

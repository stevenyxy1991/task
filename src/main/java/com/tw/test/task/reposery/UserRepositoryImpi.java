package com.tw.test.task.reposery;

import com.tw.test.task.dao.IUserDao;
import com.tw.test.task.entity.UserEntityPo;
import com.tw.test.task.util.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpi implements IUserRepository {

    @Autowired
    private TaskRepositoryImpi taskRepositoryImpi;

    @Autowired
    private IUserDao userDao;

    @Override
    public OperationType operationDef(){
        return OperationType.JPA;
    }

    @Override
    public void deleteUserById(Long id) {
        userDao.deleteById(id);
        taskRepositoryImpi.deleteByUserId(id);
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

}

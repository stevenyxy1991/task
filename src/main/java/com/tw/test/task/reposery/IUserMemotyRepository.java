package com.tw.test.task.reposery;


import com.tw.test.task.model.UserINModel;
import com.tw.test.task.model.UserOutModel;
import com.tw.test.task.util.OperationType;

public interface IUserMemotyRepository {

    void save(UserINModel userINModel);

    void update(UserINModel userINModel);

    void shareTask(UserINModel userINModel);

    void deleteUserById(Long id);

    UserOutModel getUserById(Long id);

}


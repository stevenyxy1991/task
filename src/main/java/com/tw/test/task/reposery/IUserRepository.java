package com.tw.test.task.reposery;


import com.tw.test.task.entity.UserEntityPo;
import com.tw.test.task.util.OperationType;

public interface IUserRepository extends BaseRepository<UserEntityPo>{

    OperationType operationDef();

//    void shoreTask(UserINModel userINModel);

    void deleteUserById(Long id);

}

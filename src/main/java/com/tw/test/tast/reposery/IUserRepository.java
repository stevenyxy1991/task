package com.tw.test.tast.reposery;


import com.tw.test.tast.entity.UserEntityPo;
import com.tw.test.tast.util.OperationType;

public interface IUserRepository extends BaseRepository<UserEntityPo>{

    OperationType operationDef();

//    void shoreTask(UserINModel userINModel);

    void deleteUserById(Long id);

}

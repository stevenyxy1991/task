package com.tw.test.task.reposery;


import com.tw.test.task.entity.TaskEntityPo;
import com.tw.test.task.entity.UserEntityPo;
import com.tw.test.task.model.UserModel;
import com.tw.test.task.model.UserOutModel;
import com.tw.test.task.util.OperationType;

public interface IUserRepository extends BaseRepository<UserEntityPo>{

    OperationType operationDef();

    void update(UserModel t);

    void save(UserModel t);

    UserOutModel getByIdInfo(Long id);

}

package com.tw.test.task.reposery;


import com.tw.test.task.model.UserINModel;
import com.tw.test.task.util.OperationType;

public interface IUserMemotyRepository extends BaseRepository<UserINModel>{

    OperationType operationDef();

}

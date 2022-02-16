package com.tw.test.tast.reposery;


import com.tw.test.tast.entity.UserEntityPo;
import com.tw.test.tast.model.UserINModel;
import com.tw.test.tast.util.OperationType;

public interface IUserMemotyRepository extends BaseRepository<UserINModel>{

    OperationType operationDef();

}

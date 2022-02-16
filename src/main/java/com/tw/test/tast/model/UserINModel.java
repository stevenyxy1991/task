package com.tw.test.tast.model;

import com.tw.test.tast.entity.BaseEntity;
import com.tw.test.tast.entity.TaskEntity;
import com.tw.test.tast.entity.UserEntity;
import com.tw.test.tast.entity.UserEntityPo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class UserINModel extends BaseEntity {

    public UserINModel() {
    }

    private UserEntity userEntity;

    private Long shareId;

    private List<TaskEntity> taskEntitys;

}

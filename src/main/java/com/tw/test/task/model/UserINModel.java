package com.tw.test.task.model;

import com.tw.test.task.entity.BaseEntity;
import com.tw.test.task.entity.TaskEntity;
import com.tw.test.task.entity.UserEntity;
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

package com.tw.test.task.model;

import com.tw.test.task.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserModel extends BaseEntity {

    private UserEntityPo userEntity;

    private List<TaskEntityPo> taskEntitys;

}

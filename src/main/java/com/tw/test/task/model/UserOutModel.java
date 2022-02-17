package com.tw.test.task.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tw.test.task.entity.BaseEntity;
import com.tw.test.task.entity.TaskEntityPo;
import com.tw.test.task.entity.UserEntity;
import com.tw.test.task.entity.UserEntityPo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserOutModel extends BaseEntity {

    private UserEntityPo userEntity;

    private List<TaskEntityPo> taskEntitys;
}

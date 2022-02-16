package com.tw.test.task.model;

import com.tw.test.task.entity.BaseEntity;
import com.tw.test.task.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@AllArgsConstructor
@Configuration
@Data
public class UserOutModel extends BaseEntity {


    private UserEntity userEntity;

    private List<TaskOutModel> taskEntitys;

    public UserOutModel() {
    }
}

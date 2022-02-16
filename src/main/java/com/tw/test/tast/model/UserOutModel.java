package com.tw.test.tast.model;

import com.tw.test.tast.entity.BaseEntity;
import com.tw.test.tast.entity.UserEntity;
import com.tw.test.tast.entity.UserEntityPo;
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

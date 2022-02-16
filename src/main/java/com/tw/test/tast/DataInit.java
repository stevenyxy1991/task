package com.tw.test.tast;

import com.tw.test.tast.entity.TaskEntity;
import com.tw.test.tast.entity.UserEntity;
import com.tw.test.tast.entity.UserEntityPo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataInit {

    @Bean
    public List<TaskEntity> taskInit(){
        final List<TaskEntity> taskEntities = new ArrayList<>(10);
        taskEntities.add(new TaskEntity(1L,"working", LocalDateTime.now(),LocalDateTime.now().plusDays(5),"",LocalDateTime.now()));
        taskEntities.add(new TaskEntity(2L,"eating", LocalDateTime.now(),LocalDateTime.now().plusDays(10),"",LocalDateTime.now()));
        taskEntities.add(new TaskEntity(3L,"sleeping", LocalDateTime.now(),LocalDateTime.now().plusDays(15),"",LocalDateTime.now()));
        return taskEntities;
    }

    @Bean
    public List<UserEntity> userInit(){
        final List<UserEntity> userEntities = new ArrayList<>(10);
        userEntities.add(new UserEntity(1L,"steven",34,LocalDateTime.now()));
        userEntities.add(new UserEntity(2L,"jackey",44,LocalDateTime.now()));
        userEntities.add(new UserEntity(3L,"rose",24,LocalDateTime.now()));
        return userEntities;
    }

}

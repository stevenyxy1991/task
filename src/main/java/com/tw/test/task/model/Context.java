package com.tw.test.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: task
 * @description: context
 * @author: Mr.Wang
 * @create: 2022-02-18 15:01
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Configuration
public class  Context {

    public Map<String,Object> context;

    @Bean
    public void initContext(){
        context = new HashMap<>();
        context.put("optionType","JPA");
    }
}

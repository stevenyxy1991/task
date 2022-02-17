package com.tw.test.task.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends BaseEntity{

    private Long id;

    private String name;

    private int age;

    private LocalDateTime timestemp;

}

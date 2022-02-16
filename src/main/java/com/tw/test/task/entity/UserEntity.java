package com.tw.test.task.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

//@Table(name = "task_user")
//@Entity
@Data
@AllArgsConstructor
public class UserEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "timestemp")
    private LocalDateTime timestemp;

//    @OneToMany(cascade = CascadeType.ALL,mappedBy = "userId")
//    private List<TaskEntityPo> taskEntityPos;

    public UserEntity() {
    }
}

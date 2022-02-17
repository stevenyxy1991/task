package com.tw.test.task.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task_user")
@Entity
@Data
@ToString
public class UserEntityPo extends BaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "age")
    public int age;

    @Column(name = "timestemp")
    public LocalDateTime timestemp;

}

package com.tw.test.task.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "task")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class TaskEntityPo extends BaseEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "startTime")
    public LocalDateTime startTime;

    @Column(name = "endTime")
    public LocalDateTime endTime;

    @Column(name = "descreption")
    public String descreption;

    @Column(name = "timestemp")
    public LocalDateTime timestemp;

    @Column(name = "relation")
    public String relation;

    @Column(name = "userId")
    public String userId;


}

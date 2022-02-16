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
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "startTime")
    private LocalDateTime startTime;

    @Column(name = "endTime")
    private LocalDateTime endTime;

    @Column(name = "descreption")
    private String descreption;

    @Column(name = "timestemp")
    private LocalDateTime timestemp;

    @Column(name = "relation")
    private String relation;

    @Column(name = "userId")
    private String userId;


}

package com.tw.test.task.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity extends BaseEntity{

    private Long id;

    private String name;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String descreption;

    private LocalDateTime timestemp;

}

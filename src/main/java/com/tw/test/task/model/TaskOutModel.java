package com.tw.test.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TaskOutModel {

    private Long id;

    private String name;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String descreption;

    private LocalDateTime timestemp;

    private Long shareId;

    public TaskOutModel() { 
    }
}

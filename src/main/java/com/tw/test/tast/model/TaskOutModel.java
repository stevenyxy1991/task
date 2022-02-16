package com.tw.test.tast.model;

import com.tw.test.tast.entity.TaskEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

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

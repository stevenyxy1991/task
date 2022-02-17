package com.tw.test.task.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContectEntity extends BaseEntity{

    private Long userId;

    private Long taskId;

    private Boolean isShare = false;

    private Long shardedId;

    private LocalDateTime timestemp;
}

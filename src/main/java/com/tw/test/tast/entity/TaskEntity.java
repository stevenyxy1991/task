package com.tw.test.tast.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

//@Table(name = "TASK")
//@Entity
@Data
@AllArgsConstructor
public class TaskEntity extends BaseEntity{
//    @Id
//    @Column(name = "ID", nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "NAME", nullable = false)
    private String name;

//    @Column(name = "START_TIME", nullable = false)
    private LocalDateTime startTime;

//    @Column(name = "END_TIME", nullable = false)
    private LocalDateTime endTime;

//    @Column(name = "DESCREPTION", nullable = false)
    private String descreption;

//    @Column(name = "TIMESTEMP", nullable = false)
    private LocalDateTime timestemp;

    public TaskEntity() {
    }
}

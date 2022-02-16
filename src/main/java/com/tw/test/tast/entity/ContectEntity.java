package com.tw.test.tast.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

//@Table(name = "USER")
//@Entity
@Data
@AllArgsConstructor
public class ContectEntity extends BaseEntity{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Long id;

//    @Column(name = "name", nullable = false)
    private Long userId;

    private Long taskId;

    private Boolean isShare = false;

    private Long shardedId;

//    @Column(name = "timestemp", nullable = false)
    private LocalDateTime timestemp;

    public ContectEntity() {

    }
}

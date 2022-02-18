package com.tw.test.task.service;

import com.tw.test.task.entity.BaseEntity;

import java.util.List;

public interface BaseService<T extends BaseEntity> {

    void deleteById(Long id);

}

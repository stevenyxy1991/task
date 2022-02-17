package com.tw.test.task.service;

import com.tw.test.task.entity.BaseEntity;

import java.util.List;

public interface BaseService<T extends BaseEntity> {

    T save(T t);

    T update(T t);

    void deleteById(Long id);

    T getById(Long id);

    List<T> queryAll();

    List<T> queryAll(int page, int pageSize);


}

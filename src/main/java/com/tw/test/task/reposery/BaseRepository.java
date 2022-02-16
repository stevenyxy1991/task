package com.tw.test.task.reposery;

import com.tw.test.task.entity.BaseEntity;

import java.util.List;

public interface BaseRepository<T extends BaseEntity> {

    void save(T t);

    void update(T t);

    void deleteById(Long id);

    T getById(Long id);

    List<T> queryAll();

    List<T> queryAll(int start, int pageSize);


}

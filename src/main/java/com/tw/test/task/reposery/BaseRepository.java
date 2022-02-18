package com.tw.test.task.reposery;

import com.tw.test.task.entity.BaseEntity;

import java.util.List;

public interface BaseRepository<T extends BaseEntity> {

    public static final String SYMBOL ="#";

    public static final String PERCENT = "%";

    void deleteById(Long id);

    List<T> queryAll();


}

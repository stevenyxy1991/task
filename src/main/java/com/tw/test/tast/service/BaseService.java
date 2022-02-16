package com.tw.test.tast.service;

import com.tw.test.tast.entity.BaseEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseService<T extends BaseEntity> {

    T save(T t);

    T update(T t);

    T deleteById(Long id);

    T getById(Long id);

    List<T> queryAll();

    List<T> queryAll(int page, int pageSize);


}

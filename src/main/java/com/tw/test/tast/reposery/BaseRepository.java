package com.tw.test.tast.reposery;

import com.tw.test.tast.entity.BaseEntity;
import com.tw.test.tast.util.OperationType;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseRepository<T extends BaseEntity> {

    void save(T t);

    void update(T t);

    void deleteById(Long id);

    T getById(Long id);

    List<T> queryAll();

    List<T> queryAll(int start, int pageSize);


}

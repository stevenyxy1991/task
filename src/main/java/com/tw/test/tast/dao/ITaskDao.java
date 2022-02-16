package com.tw.test.tast.dao;

import com.tw.test.tast.entity.TaskEntityPo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaskDao extends JpaRepository<TaskEntityPo,Long> {

    void deleteAllByUserId(String id);

}

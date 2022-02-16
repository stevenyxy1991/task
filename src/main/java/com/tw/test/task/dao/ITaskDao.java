package com.tw.test.task.dao;

import com.tw.test.task.entity.TaskEntityPo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaskDao extends JpaRepository<TaskEntityPo,Long> {

    void deleteAllByUserId(String id);

}

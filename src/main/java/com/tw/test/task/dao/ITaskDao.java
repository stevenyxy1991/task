package com.tw.test.task.dao;

import com.tw.test.task.entity.TaskEntityPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ITaskDao extends JpaRepository<TaskEntityPo,Long>, JpaSpecificationExecutor {


    @Modifying
    @Query("delete from TaskEntityPo where relation like :userId")
    void deleteByRelation(@Param("userId") String userId);

    List<TaskEntityPo> findAllByRelationLike(String like);

}

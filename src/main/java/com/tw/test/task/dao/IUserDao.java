package com.tw.test.task.dao;

import com.tw.test.task.entity.UserEntityPo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDao extends JpaRepository<UserEntityPo,Long> {


}

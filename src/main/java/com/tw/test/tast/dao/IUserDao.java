package com.tw.test.tast.dao;

import com.tw.test.tast.entity.TaskEntityPo;
import com.tw.test.tast.entity.UserEntityPo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDao extends JpaRepository<UserEntityPo,Long> {


}

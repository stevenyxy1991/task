package com.tw.test.task.controller;

import com.tw.test.task.entity.UserEntityPo;
import com.tw.test.task.model.UserINModel;
import com.tw.test.task.model.UserModel;
import com.tw.test.task.model.UserOutModel;
import com.tw.test.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

/**
* @description: user API
* @author: xinyu yang
* @create: 2022/2/17
**/
@RestController
@RequestMapping(value = "/user")
@Transactional
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/save")
    public String save(@RequestBody UserModel user) {
        userService.saveUserTask(user);
        return "success";
    }

    @PutMapping(value = "/update")
    public UserEntityPo update(@RequestBody UserEntityPo user) {
        return userService.update(user);
    }

    @DeleteMapping(value = "/id/{id}")
    public String delete(@PathVariable  Long id) {
        userService.deleteById(id);
        return "success";
    }

    @GetMapping(value = "/id/{id}")
    public UserOutModel findById (@PathVariable Long id) {
        return userService.getByIdInfo(id);
    }

    @GetMapping(value = "/findAll")
    public List<UserEntityPo> findAll () {
        return userService.queryAll();
    }

    @PostMapping(value = "/share/task")
    public String share(@RequestBody UserModel user) {
        userService.share(user);
        return "success";
    }
}

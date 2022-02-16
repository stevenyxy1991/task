package com.tw.test.tast.controller;

import com.tw.test.tast.entity.UserEntityPo;
import com.tw.test.tast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
@Transactional
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/save")
    public String save(@RequestBody UserEntityPo user) {
        userService.save(user);
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
    public UserEntityPo findById (@PathVariable Long id) {
        return userService.getById(id);
    }


    @GetMapping(value = "/findAll")
    public List<UserEntityPo> findAll () {
        return userService.queryAll();
    }
}

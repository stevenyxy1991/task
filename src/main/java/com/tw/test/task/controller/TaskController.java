package com.tw.test.task.controller;


import com.tw.test.task.entity.TaskEntityPo;
import com.tw.test.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping(value = "/save")
    public String save(@RequestBody TaskEntityPo user) {
        taskService.save(user);
        return "success";
    }

    @PutMapping(value = "/update")
    public TaskEntityPo update(@RequestBody TaskEntityPo user) {
        return taskService.update(user);
    }

    @DeleteMapping(value = "/id/{id}")
    public String delete(@PathVariable  Long id) {
        taskService.deleteById(id);
        return "删除成功";
    }

    @GetMapping(value = "/id/{id}")
    public TaskEntityPo findById (@PathVariable Long id) {
        return taskService.getById(id);
    }

    @GetMapping(value = "/findAll")
    public List<TaskEntityPo> findAll (int page, int pageSize) {
        return taskService.queryAll(page,pageSize);
    }
}

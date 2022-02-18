package com.tw.test.task.controller;


import com.tw.test.task.entity.TaskEntityPo;
import com.tw.test.task.model.TaskShareModel;
import com.tw.test.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @description: Task API
* @author: xinyu yang
* @create: 2022/2/17
**/
@RestController
@RequestMapping(value = "/task")
@Component
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping(value = "/save")
    public String save(@RequestBody TaskEntityPo task) {
        taskService.save(task);
        return "success";
    }

    @PutMapping(value = "/update")
    public TaskEntityPo update(@RequestBody TaskEntityPo task) {
        return taskService.update(task);
    }

    @DeleteMapping(value = "/id/{id}")
    public String delete(@PathVariable  Long id) {
        taskService.deleteById(id);
        return "success";
    }

    @GetMapping(value = "/id/{id}")
    public TaskEntityPo findById (@PathVariable Long id) {
        return taskService.getById(id);
    }

    @GetMapping(value = "/findAll")
    public List<TaskEntityPo> findAll () {
        return taskService.queryAll();
    }

    @PostMapping(value = "/share/task")
    public String share(@RequestBody TaskShareModel task) {
        taskService.share(task);
        return "success";
    }
}

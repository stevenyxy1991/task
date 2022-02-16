//package com.tw.test.tast.controller;
//
//import com.tw.test.tast.entity.TaskEntity;
//import com.tw.test.tast.service.MemoryService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(value = "/memory/task")
//@Slf4j
//public class MemoryController {
//
//    @Autowired
//    private MemoryService memoryService;
//
//    @PostMapping(value = "/save")
//    public TaskEntity save(@RequestBody TaskEntity task) {
//        return memoryService.save(task);
//    }
//
//    @PutMapping(value = "/update")
//    public TaskEntity update(@RequestBody TaskEntity task) {
//        return memoryService.update(task);
//    }
//
//    @DeleteMapping(value = "/id/{id}")
//    public String delete(@PathVariable  Long id) {
//        memoryService.deleteById(id);
//        return "删除成功";
//    }
//
//    @GetMapping(value = "/id/{id}")
//    public TaskEntity findById (@PathVariable Long id) {
//        return memoryService.getById(id);
//    }
//
////    @GetMapping(value = "/username/{username}")
////    public TaskEntity findByUsername (@PathVariable String username) {
////        return memoryService.getByName(username);
////    }
//
//    @GetMapping(value = "/findAll")
//    public List<TaskEntity> findAll () {
//        log.debug("hasdouhfqsao*****************");
//        return memoryService.queryAll();
//    }
//}

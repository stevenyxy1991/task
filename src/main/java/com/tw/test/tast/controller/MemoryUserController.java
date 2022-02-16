//package com.tw.test.tast.controller;
//
//import com.tw.test.tast.model.UserINModel;
//import com.tw.test.tast.model.UserOutModel;
//import com.tw.test.tast.service.MemoryUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(value = "/memory/user")
//public class MemoryUserController {
//
//    @Autowired
//    private MemoryUserService memoryUserService;
//
//    @PostMapping(value = "/save")
//    public String save(@RequestBody UserINModel user) {
//        memoryUserService.save(user);
//        return "success";
//    }
//
//    @PostMapping(value = "/share")
//    public String share(@RequestBody UserINModel user) {
//        memoryUserService.shareTask(user);
//        return "success";
//    }
//
//    @PutMapping(value = "/update")
//    public UserINModel update(@RequestBody UserINModel user) {
//        return memoryUserService.update(user);
//    }
//
//    @DeleteMapping(value = "/id/{id}")
//    public String delete(@PathVariable  Long id) {
//        memoryUserService.deleteById(id);
//        return "success";
//    }
//
//    @GetMapping(value = "/id/{id}")
//    public UserOutModel findById (@PathVariable Long id) {
//        return memoryUserService.getUserById(id);
//    }
//
////    @GetMapping(value = "/username/{username}")
////    public UserINModel findByUsername (@PathVariable String username) {
////        return memoryUserService.getByName(username);
////    }
////
////    @GetMapping(value = "/findAll")
////    public List<UserINModel> findAll () {
////        return memoryUserService.queryAll();
////    }
//}

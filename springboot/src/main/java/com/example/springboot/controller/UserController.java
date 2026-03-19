package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.controller.request.UserPageRequest;
import com.example.springboot.entity.User;
import com.example.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;
//    新增用户的接口
    @PostMapping("/save")
    public Result save(@RequestBody User user) {
//        通过@RequestBody将json对象转换为User对象
        userService.save(user);
        return Result.success();

    }
//    在编辑时通过id查询的接口,它是update的前提
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        User user = userService.getById(id);
//        与List<User>做比，一个返回一个，一个返回多个
        return Result.success(user);

    }
//    参照getById接口
    @GetMapping("/detail/{id}")
    public Result detail(@PathVariable Integer id){
        User user = userService.detail(id);
        return Result.success();
    }
// 提交接口
    @PutMapping("/update")
    public Result update(@RequestBody User user) {
//        通过@RequestBody将json对象转换为User对象
        userService.update(user);
        return Result.success();

    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        userService.deleteById(id);
        return Result.success();
    }

//    public List<User> listUsers(){
//        return userService.listUsers();原来接口
    @GetMapping("/list")
    public Result list(){
        List<User> users = userService.list();
        return Result.success(users);

    }
    @GetMapping("/page")
    public Result page(UserPageRequest userPageRequest){
//        它的参数是一个查询的类
//        Object page = userService.page(userPageRequest);
//        page对接的是IUserService接口
        return Result.success(userService.page(userPageRequest));

    }



}
//首先用户请求接口到@GetMapping接口，通过userService调用listUsers,进入了UserService.java，在listUsers()中按ctrl+alt+b进入实现类
//接口分类：查询所有 分页查询 根据id查询 增删改
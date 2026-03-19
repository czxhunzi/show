package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.controller.dto.LoginDTO;
import com.example.springboot.controller.request.AdminPageRequest;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.PasswordRequest;
import com.example.springboot.entity.Admin;
import com.example.springboot.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    IAdminService adminService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest request) {
        LoginDTO login = adminService.login(request);
        return Result.success(login);

    }
//    重置密码接口
    @PutMapping("/password")
    public Result password(@RequestBody PasswordRequest request){
        adminService.changePass(request);
        return Result.success();
    }



//    新增用户的接口
    @PostMapping("/save")
    public Result save(@RequestBody Admin obj) {
//        通过@RequestBody将json对象转换为User对象
        adminService.save(obj);
        return Result.success();

    }
//    在编辑时通过id查询的接口,它是update的前提
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Admin obj = adminService.getById(id);
//        与List<Admin>做比，一个返回一个，一个返回多个
        return Result.success(obj);

    }
//    参照getById接口
    @GetMapping("/detail/{id}")
    public Result detail(@PathVariable Integer id){
        Admin obj = adminService.detail(id);
        return Result.success();
    }
// 提交接口
    @PutMapping("/update")
    public Result update(@RequestBody Admin obj) {
//        通过@RequestBody将json对象转换为User对象
        adminService.update(obj);
        return Result.success();

    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        adminService.deleteById(id);
        return Result.success();
    }

//    public List<Admin> listUsers(){
//        return adminService.listUsers();原来接口
    @GetMapping("/list")
    public Result list(){
        List<Admin> list = adminService.list();
        return Result.success(list);

    }
    @GetMapping("/page")
    public Result page(AdminPageRequest adminPageRequest){
//        它的参数是一个查询的类
//        Object page = adminService.page(userPageRequest);
//        page对接的是IUserService接口
        return Result.success(adminService.page(adminPageRequest));

    }



}

package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.controller.request.LocationPageRequest;
import com.example.springboot.controller.request.PublishPageRequest;
import com.example.springboot.entity.Location;
import com.example.springboot.entity.Publish;
import com.example.springboot.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    ILocationService locationService;


    //    新增用户的接口
    @PostMapping("/save")
    public Result save(@RequestBody Location obj) {
//        通过@RequestBody将json对象转换为User对象
        locationService.save(obj);
        return Result.success();

    }
    //    在编辑时通过id查询的接口,它是update的前提
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Location obj = locationService.getById(id);
//        与List<Category>做比，一个返回一个，一个返回多个
        return Result.success(obj);

    }
    //    参照getById接口
//    @GetMapping("/detail/{id}")
//    public Result detail(@PathVariable Integer id){
//        Category obj = categoryService.detail(id);
//        return Result.success();
//    }
// 提交接口
    @PutMapping("/update")
    public Result update(@RequestBody Location obj) {
//        通过@RequestBody将json对象转换为User对象
        locationService.update(obj);
        return Result.success();

    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        locationService.deleteById(id);
        return Result.success();
    }

    //    public List<Category> listUsers(){
//        return categoryService.listUsers();原来接口
    @GetMapping("/list")
    public Result list(){
        List<Location> list = locationService.list();
        return Result.success(list);

    }

    @GetMapping("/page")
    public Result page(LocationPageRequest locationPageRequest){
        return Result.success(locationService.page(locationPageRequest));

    }
}

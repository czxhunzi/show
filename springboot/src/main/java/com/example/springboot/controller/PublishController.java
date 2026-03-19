package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.controller.request.CategoryPageRequest;
import com.example.springboot.controller.request.PublishPageRequest;
import com.example.springboot.entity.Category;
import com.example.springboot.entity.Publish;
import com.example.springboot.service.ICategoryService;
import com.example.springboot.service.IPublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/publish")
public class PublishController {

    @Autowired
    IPublishService publishService;


    //    新增用户的接口
    @PostMapping("/save")
    public Result save(@RequestBody Publish obj) {
//        通过@RequestBody将json对象转换为User对象
        publishService.save(obj);
        return Result.success();

    }
    //    在编辑时通过id查询的接口,它是update的前提
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Publish obj = publishService.getById(id);
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
    public Result update(@RequestBody Publish obj) {
//        通过@RequestBody将json对象转换为User对象
        publishService.update(obj);
        return Result.success();

    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        publishService.deleteById(id);
        return Result.success();
    }

    //    public List<Category> listUsers(){
//        return categoryService.listUsers();原来接口
    @GetMapping("/list")
    public Result list(){
        List<Publish> list = publishService.list();
        return Result.success(list);

    }

    @GetMapping("/page")
    public Result page(PublishPageRequest publishPageRequest){
        return Result.success(publishService.page(publishPageRequest));

    }
}

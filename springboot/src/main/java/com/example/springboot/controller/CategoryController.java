package com.example.springboot.controller;

import cn.hutool.core.collection.CollUtil;
import com.example.springboot.common.Result;
import com.example.springboot.controller.request.AdminPageRequest;
import com.example.springboot.controller.request.CategoryPageRequest;
import com.example.springboot.entity.Category;
import com.example.springboot.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    ICategoryService categoryService;
    

//    新增用户的接口
    @PostMapping("/save")
    public Result save(@RequestBody Category obj) {
//        通过@RequestBody将json对象转换为User对象
        categoryService.save(obj);
        return Result.success();

    }
//    在编辑时通过id查询的接口,它是update的前提
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Category obj = categoryService.getById(id);
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
    public Result update(@RequestBody Category obj) {
//        通过@RequestBody将json对象转换为User对象
        categoryService.update(obj);
        return Result.success();

    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        categoryService.deleteById(id);
        return Result.success();
    }

//    public List<Category> listUsers(){
//        return categoryService.listUsers();原来接口
    @GetMapping("/list")
    public Result list(){
        List<Category> list = categoryService.list();
        return Result.success(list);

    }
//    用来做book中分类的接口
    @GetMapping("/tree")
    public Result tree(){
        List<Category> list = categoryService.list();
//        List<Category> treeList = list.stream().filter(v -> v.getPid() == null).collect(Collectors.toList());

        return Result.success(createTree(null,list));

    }
    private List<Category> createTree(Integer pid,List<Category> categories){
     List<Category> treeList = new ArrayList<>();
     for(Category category : categories){
         if(pid == null){
             if(category.getPid() == null){
                 treeList.add(category);
                 category.setChildren(createTree(category.getId(),categories));
             }
         }else {
             if(pid.equals(category.getPid())){
                 treeList.add(category);
                 category.setChildren(createTree(category.getId(),categories));
             }
         }
         if(CollUtil.isEmpty(category.getChildren())){
             category.setChildren(null);
         }

     }
     return treeList;
    }


    @GetMapping("/page")
    public Result page(CategoryPageRequest categoryPageRequest){
        return Result.success(categoryService.page(categoryPageRequest));

    }



}
//首先用户请求接口到@GetMapping接口，通过userService调用listUsers,进入了UserService.java，在listUsers()中按ctrl+alt+b进入实现类
//接口分类：查询所有 分页查询 根据id查询 增删改
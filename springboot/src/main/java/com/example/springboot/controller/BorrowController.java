package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.controller.request.BookPageRequest;
import com.example.springboot.controller.request.BorrowPageRequest;
import com.example.springboot.entity.Borrow;
import com.example.springboot.entity.Ret;
import com.example.springboot.mapper.BookMapper;
import com.example.springboot.service.IBookService;
import com.example.springboot.service.IBorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/borrow")
public class BorrowController {
    @Autowired
    IBorrowService borrowService;



//    新增用户的接口
    @PostMapping("/save")
    public Result save(@RequestBody Borrow obj) {
//        通过@RequestBody将json对象转换为User对象

        borrowService.save(obj);
        return Result.success();

    }
    @PostMapping("/saveRet")
    public Result saveRet(@RequestBody Ret obj) {
//        通过@RequestBody将json对象转换为User对象

        borrowService.saveRet(obj);
        return Result.success();

    }
//    在编辑时通过id查询的接口,它是update的前提
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Borrow obj = borrowService.getById(id);
//        与List<Borrow>做比，一个返回一个，一个返回多个
        return Result.success(obj);

    }

// 提交接口
    @PutMapping("/update")
    public Result update(@RequestBody Borrow obj) {
//        通过@RequestBody将json对象转换为User对象
        borrowService.update(obj);
        return Result.success();

    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        borrowService.deleteById(id);
        return Result.success();
    }
    @DeleteMapping("/deleteRet/{id}")
    public Result deleteRet(@PathVariable Integer id){
        borrowService.deleteRetById(id);
        return Result.success();
    }

//    public List<Borrow> listUsers(){
//        return categoryService.listUsers();原来接口
    @GetMapping("/list")
    public Result list(){
        List<Borrow> list = borrowService.list();
        return Result.success(list);

    }
    @GetMapping("/page")
    public Result page(BorrowPageRequest borrowPageRequest){
        return Result.success(borrowService.page(borrowPageRequest));

    }
    @GetMapping("/pageRet")
    public Result pageRet(BorrowPageRequest borrowPageRequest){
        return Result.success(borrowService.pageRet(borrowPageRequest));

    }
    @GetMapping("/lineCharts/{timeRange}")
    public Result lineCharts(@PathVariable String timeRange){
        return Result.success(borrowService.getCountByTimeRange(timeRange));
    }



}
//首先用户请求接口到@GetMapping接口，通过userService调用listUsers,进入了UserService.java，在listUsers()中按ctrl+alt+b进入实现类
//接口分类：查询所有 分页查询 根据id查询 增删改
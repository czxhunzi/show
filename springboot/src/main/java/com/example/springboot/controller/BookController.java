package com.example.springboot.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.example.springboot.common.Result;
import com.example.springboot.controller.request.BookPageRequest;
import com.example.springboot.controller.request.CategoryPageRequest;
import com.example.springboot.entity.Admin;
import com.example.springboot.entity.Book;
import com.example.springboot.service.IBookService;
import com.example.springboot.service.ICategoryService;
import com.example.springboot.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import static com.example.springboot.utils.TokenUtils.getCurrentAdmin;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    IBookService bookService;

    private static final String BASE_FILE_PATH = System.getProperty("user.dir") + "/files/";


//    新增用户的接口
    @PostMapping("/save")
    public Result save(@RequestBody Book obj) {
//        通过@RequestBody将json对象转换为User对象
        bookService.save(obj);
        return Result.success();

    }
//    在编辑时通过id查询的接口,它是update的前提
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Book obj = bookService.getById(id);
//        与List<Book>做比，一个返回一个，一个返回多个
        return Result.success(obj);

    }
//    参照getById接口
//    @GetMapping("/detail/{id}")
//    public Result detail(@PathVariable Integer id){
//        Book obj = categoryService.detail(id);
//        return Result.success();
//    }
// 提交接口
    @PutMapping("/update")
    public Result update(@RequestBody Book obj) {
//        通过@RequestBody将json对象转换为User对象
        bookService.update(obj);
        return Result.success();

    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        bookService.deleteById(id);
        return Result.success();
    }

//    public List<Book> listUsers(){
//        return categoryService.listUsers();原来接口
    @GetMapping("/list")
    public Result list(){
        List<Book> list = bookService.list();
        return Result.success(list);

    }
    @GetMapping("/page")
    public Result page(BookPageRequest bookPageRequest){//BookPageRequest就是前端请求的数据格式
        return Result.success(bookService.page(bookPageRequest));

    }



}
//首先用户请求接口到@GetMapping接口，通过userService调用listUsers,进入了UserService.java，在listUsers()中按ctrl+alt+b进入实现类
//接口分类：查询所有 分页查询 根据id查询 增删改
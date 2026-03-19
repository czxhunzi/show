package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.controller.request.CategoryPageRequest;
import com.example.springboot.controller.request.NotePageRequest;
import com.example.springboot.controller.request.PublishPageRequest;
import com.example.springboot.entity.Category;
import com.example.springboot.entity.Note;
import com.example.springboot.entity.Publish;
import com.example.springboot.service.ICategoryService;
import com.example.springboot.service.INoteService;
import com.example.springboot.service.IPublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    INoteService noteService;


    //    新增用户的接口
    @PostMapping("/save")
    public Result save(@RequestBody Note obj) {
//        通过@RequestBody将json对象转换为User对象
        noteService.save(obj);
        return Result.success();

    }
    //    在编辑时通过id查询的接口,它是update的前提
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Note obj = noteService.getById(id);
//        与List<Category>做比，一个返回一个，一个返回多个
        return Result.success(obj);

    }

// 提交接口
    @PutMapping("/update")
    public Result update(@RequestBody Note obj) {
//        通过@RequestBody将json对象转换为User对象
        noteService.update(obj);
        return Result.success();

    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        noteService.deleteById(id);
        return Result.success();
    }

    //    public List<Category> listUsers(){
//        return categoryService.listUsers();原来接口
    @GetMapping("/list")
    public Result list(){
        List<Note> list = noteService.list();
        return Result.success(list);

    }

    @GetMapping("/page")
    public Result page(NotePageRequest notePageRequest){
        return Result.success(noteService.page(notePageRequest));

    }
}

package com.example.springboot.service;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.PublishPageRequest;
import com.example.springboot.entity.Book;
import com.example.springboot.entity.Category;
import com.example.springboot.entity.Note;
import com.example.springboot.entity.Publish;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface INoteService {

    void save(Note obj);

    Note getById(Integer id);

    void update(Note obj);

    void deleteById(Integer id);

    List<Note> list();

    PageInfo<Note> page(BaseRequest baseRequest);
}

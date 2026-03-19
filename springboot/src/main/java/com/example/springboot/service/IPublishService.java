package com.example.springboot.service;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.PublishPageRequest;
import com.example.springboot.entity.Book;
import com.example.springboot.entity.Category;
import com.example.springboot.entity.Publish;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IPublishService {

    void save(Publish obj);

    Publish getById(Integer id);

    void update(Publish obj);

    void deleteById(Integer id);

    List<Publish> list();

    PageInfo<Publish> page(BaseRequest baseRequest);
}

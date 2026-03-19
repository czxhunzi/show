package com.example.springboot.service.impl;


import cn.hutool.core.collection.CollUtil;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Book;
import com.example.springboot.mapper.BookMapper;
import com.example.springboot.service.IBookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class BookService implements IBookService {
    @Resource
    BookMapper bookMapper;
    
    @Override
    public List<Book> list() {
        return bookMapper.list();
    }
    @Override
    public PageInfo<Book> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(),baseRequest.getPageSize());
        List<Book> books = bookMapper.listByCondition(baseRequest);
        return new PageInfo<>(books);
    }

    @Override
    public void save(Book obj) {
        obj.setCategory(category(obj.getCategories()));
        obj.setPublish(exchange(obj.getPublishes()));
        obj.setLocationId(get(obj.getLocations()));
        bookMapper.save(obj);
    }

    @Override
    public Book getById(Integer id) {
        return bookMapper.getById(id);
    }

    @Override
    public void update(Book obj) {
        obj.setCategory(category(obj.getCategories()));
        obj.setPublish(exchange(obj.getPublishes()));
        obj.setUpdatetime(LocalDate.now());
        bookMapper.updateById(obj);
    }

    @Override
    public void deleteById(Integer id) {
        bookMapper.deleteById(id);
    }
    private String category(List<String> categories){
        StringBuilder sb = new StringBuilder();
        if(CollUtil.isNotEmpty(categories)){
            categories.forEach(v -> sb.append(v).append(" > "));
            return sb.substring(0,sb.lastIndexOf(" > "));
        }
        return sb.toString();

    }

    private String exchange(List<String> publishes){
        StringBuilder sb = new StringBuilder();
        if(CollUtil.isNotEmpty(publishes)){
            publishes.forEach(v -> sb.append(v));
            return sb.toString();
        }
        return sb.toString();
    }

    private Integer get(List<Integer> locations){
        return locations.get(0);
    }
}

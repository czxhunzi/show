package com.example.springboot.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.PublishPageRequest;
import com.example.springboot.entity.Category;
import com.example.springboot.entity.Note;
import com.example.springboot.entity.Publish;
import com.example.springboot.mapper.NoteMapper;
import com.example.springboot.mapper.PublishMapper;
import com.example.springboot.service.INoteService;
import com.example.springboot.service.IPublishService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@Service
public class NoteService implements INoteService {

    @Resource
    NoteMapper noteMapper;

    @Override
    public void save(Note obj) {

        noteMapper.save(obj);
    }

    @Override
    public Note getById(Integer id) {
        return noteMapper.getById(id);
    }

    @Override
    public void update(Note obj) {
        obj.setUpdatetime(LocalDate.now());

        noteMapper.update(obj);
    }

    @Override
    public void deleteById(Integer id) {
        noteMapper.deleteById(id);
    }

    @Override
    public List<Note> list() {
        return noteMapper.list();
    }

    @Override
    public PageInfo<Note> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(),baseRequest.getPageSize());
        List<Note> publishes = noteMapper.listByCondition(baseRequest);
        return new PageInfo<>(publishes);
    }






}

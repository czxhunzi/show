package com.example.springboot.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.PublishPageRequest;
import com.example.springboot.entity.Appoint;
import com.example.springboot.entity.Category;
import com.example.springboot.entity.Note;
import com.example.springboot.entity.Publish;
import com.example.springboot.mapper.AppointMapper;
import com.example.springboot.mapper.NoteMapper;
import com.example.springboot.mapper.PublishMapper;
import com.example.springboot.service.IAppointService;
import com.example.springboot.service.INoteService;
import com.example.springboot.service.IPublishService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@Service
public class AppointService implements IAppointService {

    @Resource
    AppointMapper appointMapper;

    @Override
    public void save(Appoint obj) {

        appointMapper.save(obj);
    }

    @Override
    public Appoint getById(Integer id) {
        return appointMapper.getById(id);
    }

    @Override
    public void update(Appoint obj) {
        obj.setUpdatetime(LocalDate.now());

        appointMapper.update(obj);
    }

    @Override
    public void deleteById(Integer id) {
        appointMapper.deleteById(id);
    }

    @Override
    public List<Appoint> list() {
        return appointMapper.list();
    }

    @Override
    public PageInfo<Appoint> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(),baseRequest.getPageSize());
        List<Appoint> appoints = appointMapper.listByCondition(baseRequest);
        return new PageInfo<>(appoints);
    }






}

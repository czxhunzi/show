package com.example.springboot.service.impl;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.PublishPageRequest;
import com.example.springboot.entity.Category;
import com.example.springboot.entity.Publish;
import com.example.springboot.mapper.PublishMapper;
import com.example.springboot.service.IPublishService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@Service
public class PublishService implements IPublishService {

    @Resource
    PublishMapper publishMapper;

    @Override
    public void save(Publish obj) {
        publishMapper.save(obj);
    }

    @Override
    public Publish getById(Integer id) {
        return publishMapper.getById(id);
    }

    @Override
    public void update(Publish obj) {
        obj.setUpdatetime(LocalDate.now());
        publishMapper.update(obj);
    }

    @Override
    public void deleteById(Integer id) {
        publishMapper.deleteById(id);
    }

    @Override
    public List<Publish> list() {
        return publishMapper.list();
    }

    @Override
    public PageInfo<Publish> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(),baseRequest.getPageSize());
        List<Publish> publishes = publishMapper.listByCondition(baseRequest);
        return new PageInfo<>(publishes);
    }


}

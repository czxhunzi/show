package com.example.springboot.service;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Location;
import com.example.springboot.entity.Publish;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ILocationService {

    void save(Location obj);

    Location getById(Integer id);

    void update(Location obj);

    void deleteById(Integer id);

    List<Location> list();

    PageInfo<Location> page(BaseRequest baseRequest);
}

package com.example.springboot.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Location;
import com.example.springboot.entity.Publish;
import com.example.springboot.mapper.LocationMapper;
import com.example.springboot.mapper.PublishMapper;
import com.example.springboot.service.ILocationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@Service
public class LocationService implements ILocationService {

    @Resource
    LocationMapper locationMapper;

    @Override
    public void save(Location obj) {
        obj.setAdmin(exchange(obj.getAdmins()));
        locationMapper.save(obj);
    }

    @Override
    public Location getById(Integer id) {
        return locationMapper.getById(id);
    }

    @Override
    public void update(Location obj) {
        obj.setUpdatetime(LocalDate.now());
        obj.setAdmin(exchange(obj.getAdmins()));
        locationMapper.update(obj);
    }

    @Override
    public void deleteById(Integer id) {
        locationMapper.deleteById(id);
    }

    @Override
    public List<Location> list() {
        return locationMapper.list();
    }

    @Override
    public PageInfo<Location> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(),baseRequest.getPageSize());
        List<Location> locations = locationMapper.listByCondition(baseRequest);
        return new PageInfo<>(locations);
    }

    private Integer exchange(List<Integer> admins){
        return admins.get(0);
    }

}

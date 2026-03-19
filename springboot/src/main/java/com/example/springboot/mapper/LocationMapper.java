package com.example.springboot.mapper;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Location;
import com.example.springboot.entity.Publish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LocationMapper {//先在Publish上面按alt+enter
    void save(Location obj);//再到下面按alt+enter

    Location getById(Integer id);

    void update(Location obj);

    void deleteById(Integer id);

    List<Location> list();

    List<Location> listByCondition(BaseRequest baseRequest);
}

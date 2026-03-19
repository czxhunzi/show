package com.example.springboot.mapper;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Publish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PublishMapper {//先在Publish上面按alt+enter
    void save(Publish obj);//再到下面按alt+enter

    Publish getById(Integer id);

    void update(Publish obj);

    void deleteById(Integer id);

    List<Publish> list();

    List<Publish> listByCondition(BaseRequest baseRequest);
}

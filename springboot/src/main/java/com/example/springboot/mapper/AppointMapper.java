package com.example.springboot.mapper;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Appoint;
import com.example.springboot.entity.Note;
import com.example.springboot.entity.Publish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AppointMapper {
    void save(Appoint obj);//再到下面按alt+enter

    Appoint getById(Integer id);

    void update(Appoint obj);

    void deleteById(Integer id);

    List<Appoint> list();

    List<Appoint> listByCondition(BaseRequest baseRequest);
}

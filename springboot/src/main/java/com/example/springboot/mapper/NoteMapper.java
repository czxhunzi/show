package com.example.springboot.mapper;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Note;
import com.example.springboot.entity.Publish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoteMapper {
    void save(Note obj);//再到下面按alt+enter

    Note getById(Integer id);

    void update(Note obj);

    void deleteById(Integer id);

    List<Note> list();

    List<Note> listByCondition(BaseRequest baseRequest);
}

package com.example.springboot.mapper;

import com.example.springboot.entity.OperateLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperateLogMapper {

    void save(OperateLog obj);
}

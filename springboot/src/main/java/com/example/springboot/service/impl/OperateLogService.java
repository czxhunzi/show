package com.example.springboot.service.impl;

import com.example.springboot.entity.OperateLog;
import com.example.springboot.mapper.OperateLogMapper;
import com.example.springboot.service.IOperateLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OperateLogService implements IOperateLogService {

    @Resource
    OperateLogMapper operateLogMapper;

    @Override
    public void save(OperateLog obj) {
        operateLogMapper.save(obj);
    }
}

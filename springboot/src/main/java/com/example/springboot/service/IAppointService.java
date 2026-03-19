package com.example.springboot.service;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Appoint;
import com.example.springboot.entity.Borrow;
import com.example.springboot.entity.Ret;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface IAppointService {
    
    List<Appoint> list();

    PageInfo<Appoint> page(BaseRequest baseRequest);

    void save(Appoint obj);

//    PageInfo<Ret> pageRet(BaseRequest baseRequest);

//    void saveRet(Ret obj);

    Appoint getById(Integer id);

    void update(Appoint obj);

    void deleteById(Integer id);
//    void deleteRetById(Integer id);

//    Map <String,Object> getCountByTimeRange(String timeRange);

}

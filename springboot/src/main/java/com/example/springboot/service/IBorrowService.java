package com.example.springboot.service;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Borrow;
import com.example.springboot.entity.Ret;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface IBorrowService {
    
    List<Borrow> list();

    PageInfo<Borrow> page(BaseRequest baseRequest);

    void save(Borrow obj);

    PageInfo<Ret> pageRet(BaseRequest baseRequest);

    void saveRet(Ret obj);

    Borrow getById(Integer id);

    void update(Borrow obj);

    void deleteById(Integer id);
    void deleteRetById(Integer id);

    Map <String,Object> getCountByTimeRange(String timeRange);

}

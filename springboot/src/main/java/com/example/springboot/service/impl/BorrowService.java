package com.example.springboot.service.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Book;
import com.example.springboot.entity.Borrow;
import com.example.springboot.entity.Borrow;
import com.example.springboot.entity.Ret;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.BookMapper;
import com.example.springboot.mapper.BorrowMapper;
import com.example.springboot.mapper.po.BorrowRetCountPO;
import com.example.springboot.service.IBookService;
import com.example.springboot.service.IBorrowService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class BorrowService implements IBorrowService {
    @Resource
    BorrowMapper borrowMapper;
    @Resource
    BookMapper bookMapper;

    @Override
    public List<Borrow> list() {
        return borrowMapper.list();
    }
    @Override
    public PageInfo<Borrow> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(),baseRequest.getPageSize());
        List<Borrow> borrows = borrowMapper.listByCondition(baseRequest);
        for(Borrow borrow : borrows){
            LocalDate returnDate = borrow.getReturnDate();
            LocalDate now = LocalDate.now();
            if(now.plusDays(1).isEqual(returnDate)){
                borrow.setNote("即将到期");
            }else if(now.isEqual(returnDate)){
                borrow.setNote("已到期");
            }else if(now.isAfter(returnDate)){
                borrow.setNote("已过期");
            }else {
                borrow.setNote("正常");
            }
        }
        return new PageInfo<>(borrows);
    }

    @Override
    public PageInfo<Ret> pageRet(BaseRequest baseRequest){
        PageHelper.startPage(baseRequest.getPageNum(),baseRequest.getPageSize());
        return new PageInfo<>(borrowMapper.listRetByCondition(baseRequest));
    }
    @Override
    public void saveRet(Ret obj){
        borrowMapper.updateStatus("已归还", obj.getId());
        obj.setRealDate(LocalDate.now());
        borrowMapper.saveRet(obj);
        bookMapper.updateNumByNo(obj.getBookNo());
        Borrow borrow = borrowMapper.getById(obj.getId());
        Book book = bookMapper.getByNo(obj.getBookNo());
        Integer num = borrow.getNumber1();
        book.setNumber(book.getNumber()+num-1);
        bookMapper.updateById(book);

    }

    @Override
    public void save(Borrow obj) {
//        obj.setCategory(category(obj.getCategories()));
        Book book = bookMapper.getByNo(obj.getBookNo());
        if(Objects.isNull(book)){
            throw new ServiceException("所借图书不存在");
        }
        Integer num = book.getNumber();
        if(book.getNumber() < obj.getNumber1()){
            throw new ServiceException("图书数量不足");
        }
        book.setNumber(num - obj.getNumber1());

        bookMapper.updateById(book);
        obj.setReturnDate(LocalDate.now().plus(obj.getDays(), ChronoUnit.DAYS));
        borrowMapper.save(obj);
    }

    @Override
    public Borrow getById(Integer id) {
        return borrowMapper.getById(id);
    }


    @Override
    public void update(Borrow obj) {
//        obj.setCategory(category(obj.getCategories()));
        obj.setUpdatetime(LocalDate.now());
        borrowMapper.updateById(obj);
    }

    @Override
    public void deleteById(Integer id) {
        borrowMapper.deleteById(id);
    }

    @Override
    public void deleteRetById(Integer id){
        borrowMapper.deleteRetById(id);
    }

    @Override
    public Map<String,Object> getCountByTimeRange(String timeRange){
        Map<String, Object> map = new HashMap<>();
        Date today =new Date();
        List<DateTime> dateRange;
        switch (timeRange){
            case "week":
                dateRange = DateUtil.rangeToList(DateUtil.offsetDay(today,-6),today, DateField.DAY_OF_WEEK);
                break;
            case "month":
                dateRange = DateUtil.rangeToList(DateUtil.offsetDay(today,-29),today, DateField.DAY_OF_MONTH);
                break;
            case "month2":
                dateRange = DateUtil.rangeToList(DateUtil.offsetDay(today,-59),today, DateField.DAY_OF_MONTH);
                break;
            case "month3":
                dateRange = DateUtil.rangeToList(DateUtil.offsetDay(today,-89),today, DateField.DAY_OF_MONTH);
                break;
            default:
                dateRange = new ArrayList<>();

        }
        List<String> dateStrRange = datetimeToDateStr(dateRange);
        map.put("date",dateStrRange);
        List<BorrowRetCountPO> borrowCount = borrowMapper.getCountByTimeRange(timeRange,1);
        List<BorrowRetCountPO> returnCount = borrowMapper.getCountByTimeRange(timeRange,2);
        map.put("borrow",countList(borrowCount,dateStrRange));
        map.put("ret",countList(returnCount,dateStrRange));
        return map;

    }

    private List<Integer> countList(List<BorrowRetCountPO> countPOList, List<String> dateRange) {
        List<Integer> list = CollUtil.newArrayList();
        if(CollUtil.isEmpty(countPOList)){
            return list;
        }
        for(String date : dateRange){
            Integer count = countPOList.stream().filter(countPO -> date.equals(countPO.getDate()))
                            .map(BorrowRetCountPO::getCount).findFirst().orElse(0);
            list.add(count);
        }
        return list;
    }

    private List<String> datetimeToDateStr(List<DateTime> dateTimeList) {
        List<String> list = CollUtil.newArrayList();
        if(CollUtil.isEmpty(dateTimeList)){
            return list;
        }
        for(DateTime dateTime : dateTimeList){
            String date = DateUtil.formatDate(dateTime);
            list.add(date);
        }
        return list;
    }

}

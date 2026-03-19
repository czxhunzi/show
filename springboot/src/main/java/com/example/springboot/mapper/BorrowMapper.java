package com.example.springboot.mapper;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Borrow;
import com.example.springboot.entity.Ret;
import com.example.springboot.mapper.po.BorrowRetCountPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BorrowMapper {
    List<Borrow> list();

    List<Borrow> listByCondition(BaseRequest baseRequest);
    List<Ret> listRetByCondition(BaseRequest baseRequest);

    void save(Borrow obj);
    void saveRet(Ret obj);

    Borrow getById(Integer id);

    void updateById(Borrow obj);

    void deleteById(Integer id);

    void deleteRetById(Integer id);

    void updateStatus(String status, Integer id);

    List<BorrowRetCountPO> getCountByTimeRange(@Param("timeRange") String timeRange, @Param("type") int type);

//    Borrow detail(Integer id);

//    Borrow getByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
//
//    int updatePassword(PasswordRequest request);
}

package com.my.service;

import com.my.pojo.Empinfor;
import com.my.pojo.EmpinforExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmpinforService {
    long countByExample(EmpinforExample example);

    int deleteByExample(EmpinforExample example);

    int deleteByPrimaryKey(Integer inforId);

    int insert(Empinfor record);

    int insertSelective(Empinfor record);

    List<Empinfor> selectByExample(EmpinforExample example);

    Empinfor selectByPrimaryKey(Integer inforId);

    int updateByExampleSelective(@Param("record") Empinfor record, @Param("example") EmpinforExample example);

    int updateByExample(@Param("record") Empinfor record, @Param("example") EmpinforExample example);

    int updateByPrimaryKeySelective(Empinfor record);

    int updateByPrimaryKey(Empinfor record);
}
package com.my.service;

import com.my.pojo.Depart;
import com.my.pojo.DepartExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartService {
    long countByExample(DepartExample example);

    int deleteByExample(DepartExample example);

    int deleteByPrimaryKey(Integer departId);

    int insert(Depart record);

    int insertSelective(Depart record);

    List<Depart> selectByExample(DepartExample example);

    Depart selectByPrimaryKey(Integer departId);

    int updateByExampleSelective(@Param("record") Depart record, @Param("example") DepartExample example);

    int updateByExample(@Param("record") Depart record, @Param("example") DepartExample example);

    int updateByPrimaryKeySelective(Depart record);

    int updateByPrimaryKey(Depart record);


}
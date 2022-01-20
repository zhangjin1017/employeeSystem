package com.my.dao;

import com.my.pojo.Advice;
import com.my.pojo.AdviceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdviceMapper {
    long countByExample(AdviceExample example);

    int deleteByExample(AdviceExample example);

    int deleteByPrimaryKey(Integer adviceId);

    int insert(Advice record);

    int insertSelective(Advice record);

    List<Advice> selectByExample(AdviceExample example);

    Advice selectByPrimaryKey(Integer adviceId);

    int updateByExampleSelective(@Param("record") Advice record, @Param("example") AdviceExample example);

    int updateByExample(@Param("record") Advice record, @Param("example") AdviceExample example);

    int updateByPrimaryKeySelective(Advice record);

    int updateByPrimaryKey(Advice record);
}
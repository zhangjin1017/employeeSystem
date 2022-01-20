package com.my.dao;

import com.my.pojo.State;
import com.my.pojo.StateExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StateMapper {
    long countByExample(StateExample example);

    int deleteByExample(StateExample example);

    int deleteByPrimaryKey(Integer stateId);

    int insert(State record);

    int insertSelective(State record);

    List<State> selectByExample(StateExample example);

    State selectByPrimaryKey(Integer stateId);

    int updateByExampleSelective(@Param("record") State record, @Param("example") StateExample example);

    int updateByExample(@Param("record") State record, @Param("example") StateExample example);

    int updateByPrimaryKeySelective(State record);

    int updateByPrimaryKey(State record);
}
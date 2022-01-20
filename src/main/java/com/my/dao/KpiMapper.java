package com.my.dao;

import com.my.pojo.Kpi;
import com.my.pojo.KpiExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KpiMapper {
    long countByExample(KpiExample example);

    int deleteByExample(KpiExample example);

    int deleteByPrimaryKey(Integer kpiId);

    int insert(Kpi record);

    int insertSelective(Kpi record);

    List<Kpi> selectByExample(KpiExample example);

    Kpi selectByPrimaryKey(Integer kpiId);

    int updateByExampleSelective(@Param("record") Kpi record, @Param("example") KpiExample example);

    int updateByExample(@Param("record") Kpi record, @Param("example") KpiExample example);

    int updateByPrimaryKeySelective(Kpi record);

    int updateByPrimaryKey(Kpi record);
}
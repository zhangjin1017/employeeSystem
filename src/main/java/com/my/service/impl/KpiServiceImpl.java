package com.my.service.impl;

import com.my.dao.KpiMapper;
import com.my.pojo.Kpi;
import com.my.pojo.KpiExample;
import com.my.service.KpiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KpiServiceImpl implements KpiService {
    @Autowired
    private KpiMapper kpiMapper;

    @Override
    public long countByExample(KpiExample example) {
        return kpiMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(KpiExample example) {
        return kpiMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer kpiId) {
        return kpiMapper.deleteByPrimaryKey(kpiId);
    }

    @Override
    public int insert(Kpi record) {
        return kpiMapper.insert(record);
    }

    @Override
    public int insertSelective(Kpi record) {
        return kpiMapper.insertSelective(record);
    }

    @Override
    public List<Kpi> selectByExample(KpiExample example) {
        return kpiMapper.selectByExample(example);
    }

    @Override
    public Kpi selectByPrimaryKey(Integer kpiId) {
        return kpiMapper.selectByPrimaryKey(kpiId);
    }

    @Override
    public int updateByExampleSelective(Kpi record, KpiExample example) {
        return kpiMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Kpi record, KpiExample example) {
        return kpiMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(Kpi record) {
        return kpiMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Kpi record) {
        return kpiMapper.updateByPrimaryKey(record);
    }
}
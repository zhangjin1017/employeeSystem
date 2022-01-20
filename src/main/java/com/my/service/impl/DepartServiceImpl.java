package com.my.service.impl;

import com.my.dao.DepartMapper;
import com.my.pojo.Depart;
import com.my.pojo.DepartExample;
import com.my.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartServiceImpl implements DepartService {
    @Autowired
    private DepartMapper departMapper;

    @Override
    public long countByExample(DepartExample example) {
        return departMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(DepartExample example) {
        return departMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer departId) {
        return departMapper.deleteByPrimaryKey(departId);
    }

    @Override
    public int insert(Depart record) {
        return departMapper.insert(record);
    }

    @Override
    public int insertSelective(Depart record) {
        return departMapper.insertSelective(record);
    }

    @Override
    public List<Depart> selectByExample(DepartExample example) {
        return departMapper.selectByExample(example);
    }

    @Override
    public Depart selectByPrimaryKey(Integer departId) {
        return departMapper.selectByPrimaryKey(departId);
    }

    @Override
    public int updateByExampleSelective(Depart record, DepartExample example) {
        return departMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Depart record, DepartExample example) {
        return departMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Depart record) {
        return departMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Depart record) {
        return departMapper.updateByPrimaryKey(record);
    }


}
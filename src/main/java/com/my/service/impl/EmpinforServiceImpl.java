package com.my.service.impl;

import com.my.dao.EmpinforMapper;
import com.my.pojo.Empinfor;
import com.my.pojo.EmpinforExample;
import com.my.service.EmpinforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpinforServiceImpl implements EmpinforService {
    @Autowired
    private EmpinforMapper empinforMapper;

    @Override
    public long countByExample(EmpinforExample example) {
        return empinforMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(EmpinforExample example) {
        return empinforMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer inforId) {
        return empinforMapper.deleteByPrimaryKey(inforId);
    }

    @Override
    public int insert(Empinfor record) {
        return empinforMapper.insert(record);
    }

    @Override
    public int insertSelective(Empinfor record) {
        return empinforMapper.insertSelective(record);
    }

    @Override
    public List<Empinfor> selectByExample(EmpinforExample example) {
        return empinforMapper.selectByExample(example);
    }

    @Override
    public Empinfor selectByPrimaryKey(Integer inforId) {
        return empinforMapper.selectByPrimaryKey(inforId);
    }

    @Override
    public int updateByExampleSelective(Empinfor record, EmpinforExample example) {
        return empinforMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Empinfor record, EmpinforExample example) {
        return empinforMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Empinfor record) {
        return empinforMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Empinfor record) {
        return empinforMapper.updateByPrimaryKey(record);
    }
}
package com.my.service.impl;

import com.my.dao.ApplyMapper;
import com.my.pojo.Apply;
import com.my.pojo.ApplyExample;
import com.my.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyServiceImpl implements ApplyService {
    @Autowired
    private ApplyMapper applyMapper;

    @Override
    public long countByExample(ApplyExample example) {
        return applyMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(ApplyExample example) {
        return applyMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer applyId) {
        return applyMapper.deleteByPrimaryKey(applyId);
    }

    @Override
    public int insert(Apply record) {
        return applyMapper.insert(record);
    }

    @Override
    public int insertSelective(Apply record) {
        return applyMapper.insertSelective(record);
    }

    @Override
    public List<Apply> selectByExample(ApplyExample example) {
        return applyMapper.selectByExample(example);
    }

    @Override
    public Apply selectByPrimaryKey(Integer applyId) {
        return applyMapper.selectByPrimaryKey(applyId);
    }

    @Override
    public int updateByExampleSelective(Apply record, ApplyExample example) {
        return applyMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Apply record, ApplyExample example) {
        return applyMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Apply record) {
        return applyMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Apply record) {
        return applyMapper.updateByPrimaryKey(record);
    }
}
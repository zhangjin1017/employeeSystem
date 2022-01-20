package com.my.service.impl;

import com.my.dao.AdviceMapper;
import com.my.pojo.Advice;
import com.my.pojo.AdviceExample;
import com.my.service.AdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdviceServiceImpl implements AdviceService {

    @Autowired
    private AdviceMapper adviceMapper;

    @Override
    public long countByExample(AdviceExample example) {
        return adviceMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(AdviceExample example) {
        return adviceMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer adviceId) {
        return deleteByPrimaryKey(adviceId);
    }

    @Override
    public int insert(Advice record) {
        return adviceMapper.insert(record);
    }

    @Override
    public int insertSelective(Advice record) {
        return adviceMapper.insertSelective(record);
    }

    @Override
    public List<Advice> selectByExample(AdviceExample example) {
        return adviceMapper.selectByExample(example);
    }

    @Override
    public Advice selectByPrimaryKey(Integer adviceId) {
        return adviceMapper.selectByPrimaryKey(adviceId);
    }

    @Override
    public int updateByExampleSelective(Advice record, AdviceExample example) {
        return adviceMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(Advice record, AdviceExample example) {
        return adviceMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Advice record) {
        return adviceMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Advice record) {
        return adviceMapper.updateByPrimaryKey(record);
    }
}
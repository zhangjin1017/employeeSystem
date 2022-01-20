package com.my.service.impl;

import com.my.dao.StateMapper;
import com.my.pojo.State;
import com.my.pojo.StateExample;
import com.my.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateServiceImpl implements StateService {
    @Autowired
    private StateMapper stateMapper;

    @Override
    public long countByExample(StateExample example) {
        return stateMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(StateExample example) {
        return stateMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer stateId) {
        return stateMapper.deleteByPrimaryKey(stateId);
    }

    @Override
    public int insert(State record) {
        return stateMapper.insert(record);
    }

    @Override
    public int insertSelective(State record) {
        return stateMapper.insertSelective(record);
    }

    @Override
    public List<State> selectByExample(StateExample example) {
        return stateMapper.selectByExample(example);
    }

    @Override
    public State selectByPrimaryKey(Integer stateId) {
        return stateMapper.selectByPrimaryKey(stateId);
    }

    @Override
    public int updateByExampleSelective(State record, StateExample example) {
        return stateMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(State record, StateExample example) {
        return stateMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(State record) {
        return stateMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(State record) {
        return stateMapper.updateByPrimaryKey(record);
    }
}
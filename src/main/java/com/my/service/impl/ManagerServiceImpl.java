package com.my.service.impl;

import com.my.dao.ManagerMapper;
import com.my.pojo.Manager;
import com.my.pojo.ManagerExample;
import com.my.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public long countByExample(ManagerExample example) {
        return managerMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(ManagerExample example) {
        return managerMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer managerId) {
        return managerMapper.deleteByPrimaryKey(managerId);
    }

    @Override
    public int insert(Manager record) {
        return managerMapper.insert(record);
    }

    @Override
    public int insertSelective(Manager record) {
        return managerMapper.insertSelective(record);
    }

    @Override
    @Cacheable(value="manager")
    public List<Manager> selectByExample(ManagerExample example) {
        return managerMapper.selectByExample(example);
    }

    @Override
    @Cacheable(value="manager")
    public Manager selectByPrimaryKey(Integer managerId) {
        return managerMapper.selectByPrimaryKey(managerId);
    }

    @Override
    public int updateByExampleSelective(Manager record, ManagerExample example) {
        return managerMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Manager record, ManagerExample example) {
        return managerMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Manager record) {
        return managerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Manager record) {
        return managerMapper.updateByPrimaryKey(record);
    }
}
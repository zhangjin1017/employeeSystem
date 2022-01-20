package com.my.service.impl;

import com.my.dao.RecordMapper;
import com.my.pojo.Record;
import com.my.pojo.RecordExample;
import com.my.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordMapper recordMapper;

    @Override
    public long countByExample(RecordExample example) {
        return recordMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(RecordExample example) {
        return recordMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer recordId) {
        return recordMapper.deleteByPrimaryKey(recordId);
    }

    @Override
    public int insert(Record record) {
        return recordMapper.insert(record);
    }

    @Override
    public int insertSelective(Record record) {
        return recordMapper.insertSelective(record);
    }

    @Override
    public List<Record> selectByExample(RecordExample example) {
        return recordMapper.selectByExample(example);
    }

    @Override
    public Record selectByPrimaryKey(Integer recordId) {
        return recordMapper.selectByPrimaryKey(recordId);
    }

    @Override
    public int updateByExampleSelective(Record record, RecordExample example) {
        return recordMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Record record, RecordExample example) {
        return recordMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Record record) {
        return recordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Record record) {
        return recordMapper.updateByPrimaryKey(record);
    }
}
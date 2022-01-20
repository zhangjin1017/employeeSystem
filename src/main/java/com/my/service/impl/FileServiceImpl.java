package com.my.service.impl;

import com.my.dao.FileMapper;
import com.my.pojo.File;
import com.my.pojo.FileExample;
import com.my.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileMapper fileMapper;

    @Override
    public long countByExample(FileExample example) {
        return fileMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(FileExample example) {
        return fileMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer fileId) {
        return fileMapper.deleteByPrimaryKey(fileId);
    }

    @Override
    public int insert(File record) {
        return fileMapper.insert(record);
    }

    @Override
    public int insertSelective(File record) {
        return fileMapper.insertSelective(record);
    }

    @Override
    public List<File> selectByExample(FileExample example) {
        return fileMapper.selectByExample(example);
    }

    @Override
    public File selectByPrimaryKey(Integer fileId) {
        return fileMapper.selectByPrimaryKey(fileId);
    }

    @Override
    public int updateByExampleSelective(File record, FileExample example) {
        return fileMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(File record, FileExample example) {
        return fileMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(File record) {
        return fileMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(File record) {
        return fileMapper.updateByPrimaryKey(record);
    }
}
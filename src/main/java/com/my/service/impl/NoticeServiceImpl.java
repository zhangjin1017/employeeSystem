package com.my.service.impl;

import com.my.dao.NoticeMapper;
import com.my.pojo.Notice;
import com.my.pojo.NoticeExample;
import com.my.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public long countByExample(NoticeExample example) {
        return noticeMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(NoticeExample example) {
        return noticeMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer noticeId) {
        return noticeMapper.deleteByPrimaryKey(noticeId);
    }

    @Override
    public int insert(Notice record) {
        return noticeMapper.insert(record);
    }

    @Override
    public int insertSelective(Notice record) {
        return noticeMapper.insertSelective(record);
    }

    @Override
    public List<Notice> selectByExample(NoticeExample example) {
        return noticeMapper.selectByExample(example);
    }

    @Override
    public Notice selectByPrimaryKey(Integer noticeId) {
        return noticeMapper.selectByPrimaryKey(noticeId);
    }

    @Override
    public int updateByExampleSelective(Notice record, NoticeExample example) {
        return noticeMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Notice record, NoticeExample example) {
        return noticeMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Notice record) {
        return noticeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Notice record) {
        return noticeMapper.updateByPrimaryKey(record);
    }
}
package com.my.service.impl;

import com.my.dao.AttendanceMapper;
import com.my.pojo.Attendance;
import com.my.pojo.AttendanceExample;
import com.my.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    public long countByExample(AttendanceExample example) {
        return attendanceMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(AttendanceExample example) {
        return attendanceMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer attendanceId) {
        return attendanceMapper.deleteByPrimaryKey(attendanceId);
    }

    @Override
    public int insert(Attendance record) {
        return attendanceMapper.insert(record);
    }

    @Override
    public int insertSelective(Attendance record) {
        return attendanceMapper.insertSelective(record);
    }

    @Override
    public List<Attendance> selectByExample(AttendanceExample example) {
        return attendanceMapper.selectByExample(example);
    }

    @Override
    public Attendance selectByPrimaryKey(Integer attendanceId) {
        return attendanceMapper.selectByPrimaryKey(attendanceId);
    }

    @Override
    public int updateByExampleSelective(Attendance record, AttendanceExample example) {
        return attendanceMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(Attendance record, AttendanceExample example) {
        return attendanceMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(Attendance record) {
        return attendanceMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Attendance record) {
        return attendanceMapper.updateByPrimaryKey(record);
    }
}
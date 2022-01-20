package com.my.controller;

import com.google.gson.Gson;
import com.my.pojo.Attendance;
import com.my.pojo.AttendanceExample;
import com.my.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/attendance")
@CrossOrigin
public class attendanceController {
    Gson gson = new Gson();
    private AttendanceService attendanceService;

    @Autowired
    public void setEmployeeService(AttendanceService attendanceService) { this.attendanceService = attendanceService;
    }

    /**
     * 获取打卡信息
     * @param workId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAttendance", produces = "text/plain;charset=UTF-8")
    public String getAttendance(
            @RequestParam("workId") String workId) {
        Map<String, Object> map = new HashMap<>();
            AttendanceExample attendanceExample = new AttendanceExample();
            AttendanceExample.Criteria criteria = attendanceExample.createCriteria();
            criteria.andWorkIdEqualTo(Integer.parseInt(workId));
            Attendance attendance= attendanceService.selectByExample(attendanceExample).get(0);
            map.put("message", "success");
            map.put("attendance", gson.toJson(attendance));
        System.out.println("attendance"+attendance.toString());
        return new Gson().toJson(map);
    }







}

package com.my.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.my.pojo.Record;
import com.my.pojo.RecordExample;
import com.my.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/record")
@CrossOrigin
public class RecordController {

    Gson gson = new Gson();
    private RecordService recordService;

    @Autowired
    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
    }

    @ResponseBody
    @RequestMapping(value = "/getRecordPage", produces = "text/plain;charset=UTF-8")
    public String getRecordPage(
            @RequestParam("sousuo") String sousuo,
            @RequestParam("perPageCount") int perPageCount,
            @RequestParam("currentPage") int currentPage) {
        System.out.println(sousuo);
        System.out.println(perPageCount);
        System.out.println(currentPage);
        Map<String, Object> map = new HashMap<>();
        List<Record> recordList = new ArrayList<>();
            RecordExample recordExample = new RecordExample();
            RecordExample.Criteria criteria = recordExample.createCriteria();
            criteria.andContentLike("%" + sousuo + "%");
            recordExample.setOrderByClause("record_id DESC");
            PageHelper.startPage(currentPage, perPageCount);
            recordList = recordService.selectByExample(recordExample);
        if (recordList != null) {
            map.put("message", "success");
            PageInfo<Record> pageInfo = new PageInfo<Record>(recordList);
//            employeeService.selectByExample(null);
            map.put("pageCount", pageInfo.getPages());
            map.put("totalNum", pageInfo.getTotal());
            map.put("recordList", gson.toJson(recordList));

        } else {
            map.put("message", "记录列表为空");
        }
        return new Gson().toJson(map);
    }


}

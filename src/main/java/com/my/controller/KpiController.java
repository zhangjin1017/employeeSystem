package com.my.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.my.pojo.Kpi;
import com.my.pojo.KpiExample;
import com.my.service.KpiService;
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
@RequestMapping("/kpi")
@CrossOrigin
public class KpiController {

    Gson gson = new Gson();
    private KpiService kpiService;

    @Autowired
    public void setKpiService(KpiService kpiService) {
        this.kpiService = kpiService;
    }

    @ResponseBody
    @RequestMapping(value = "/getKpiPage", produces = "text/plain;charset=UTF-8")
    public String getKpiPage(
            @RequestParam("sousuo") String sousuo,
            @RequestParam("perPageCount") int perPageCount,
            @RequestParam("currentPage") int currentPage) {
        System.out.println(sousuo);
        System.out.println(perPageCount);
        System.out.println(currentPage);
        Map<String, Object> map = new HashMap<>();
        List<Kpi> kpiList = new ArrayList<>();
        if (sousuo.equals("")) {
            KpiExample kpiExample = new KpiExample();
            KpiExample.Criteria criteria = kpiExample.createCriteria();
//            criteria.andWorkId("%" + sousuo + "%");
            PageHelper.startPage(currentPage, perPageCount);
            kpiList = kpiService.selectByExample(kpiExample);
        } else {
            //设置分页条件
            KpiExample kpiExample = new KpiExample();
            KpiExample.Criteria criteria = kpiExample.createCriteria();
//            criteria.andSenderLike("%" + sousuo + "%");
            PageHelper.startPage(currentPage, perPageCount);
            kpiList = kpiService.selectByExample(kpiExample);
        }
        if (kpiList != null) {
            map.put("message", "success");
            PageInfo<Kpi> pageInfo = new PageInfo<Kpi>(kpiList);
//            employeeService.selectByExample(null);
            map.put("pageCount", pageInfo.getPages());
            map.put("totalNum", pageInfo.getTotal());
            map.put("kpiList", gson.toJson(kpiList));

        } else {
            map.put("message", "绩效列表为空");
        }
        return new Gson().toJson(map);
    }


}

package com.my.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.my.pojo.Advice;
import com.my.pojo.AdviceExample;
import com.my.service.AdviceService;
import com.my.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/advice")
@CrossOrigin
public class AdviceController {

    Gson gson = new Gson();
    private AdviceService adviceService;
    private DepartService departService;

    @Autowired
    public void setAdviceService(AdviceService adviceService) {
        this.adviceService = adviceService;
    }

    @Autowired
    public void setDepartService(DepartService departService) {
        this.departService = departService;
    }


    @ResponseBody
    @RequestMapping(value = "/getAdvicePage", produces = "text/plain;charset=UTF-8")
    public String getAdvicePage(
            @RequestParam("sousuo") String sousuo,
            @RequestParam("perPageCount") int perPageCount,
            @RequestParam("currentPage") int currentPage) {
        System.out.println(sousuo);
        System.out.println(perPageCount);
        System.out.println(currentPage);
        Map<String, Object> map = new HashMap<>();
        List<Advice> adviceList = new ArrayList<>();
        if (sousuo.equals("")) {
            AdviceExample adviceExample = new AdviceExample();
            AdviceExample.Criteria criteria = adviceExample.createCriteria();
            criteria.andSenderLike("%" + sousuo + "%");
            adviceExample.setOrderByClause("advice_id DESC");
            PageHelper.startPage(currentPage, perPageCount);
            adviceList = adviceService.selectByExample(adviceExample);

        } else {
            //设置分页条件
            AdviceExample adviceExample = new AdviceExample();
            AdviceExample.Criteria criteria = adviceExample.createCriteria();
            criteria.andSenderLike("%" + sousuo + "%");
            adviceExample.setOrderByClause("advice_id DESC");
            PageHelper.startPage(currentPage, perPageCount);
            adviceList = adviceService.selectByExample(adviceExample);
        }
        if (adviceList != null) {
            map.put("message", "success");
            PageInfo<Advice> pageInfo = new PageInfo<Advice>(adviceList);
//            employeeService.selectByExample(null);
            map.put("pageCount", pageInfo.getPages());
            map.put("totalNum", pageInfo.getTotal());
            map.put("adviceList", gson.toJson(adviceList));

        } else {
            map.put("message", "反馈列表为空");
        }
        return new Gson().toJson(map);
    }


    @ResponseBody
    @RequestMapping(value = "/getMyAdvicePage", produces = "text/plain;charset=UTF-8")
    public String getMyAdvicePage(
            @RequestParam("workId") String workId,
            @RequestParam("sousuo") String sousuo,
            @RequestParam("perPageCount") int perPageCount,
            @RequestParam("currentPage") int currentPage) {
        System.out.println(workId);
        System.out.println(sousuo);
        System.out.println(perPageCount);
        System.out.println(currentPage);
        Map<String, Object> map = new HashMap<>();
        List<Advice> adviceList = new ArrayList<>();
            AdviceExample adviceExample = new AdviceExample();
            AdviceExample.Criteria criteria = adviceExample.createCriteria();
            criteria.andSenderEqualTo(workId);
            criteria.andTitleLike("%" + sousuo + "%");
        adviceExample.setOrderByClause("advice_id DESC");
            PageHelper.startPage(currentPage, perPageCount);
            adviceList = adviceService.selectByExample(adviceExample);

        if (adviceList != null) {
            map.put("message", "success");
            PageInfo<Advice> pageInfo = new PageInfo<Advice>(adviceList);
//            employeeService.selectByExample(null);
            map.put("pageCount", pageInfo.getPages());
            map.put("totalNum", pageInfo.getTotal());
            map.put("adviceList", gson.toJson(adviceList));
        } else {
            map.put("message", "反馈列表为空");
        }
        return new Gson().toJson(map);
    }

    @ResponseBody
    @RequestMapping(value = "/getMyAdvicePage1", produces = "text/plain;charset=UTF-8")
    public String getMyAdvicePage1(
            @RequestParam("workId") String workId,
            @RequestParam("sousuo") String sousuo,
            @RequestParam("perPageCount") int perPageCount,
            @RequestParam("currentPage") int currentPage) {
        System.out.println(workId);
        System.out.println(sousuo);
        System.out.println(perPageCount);
        System.out.println(currentPage);
        Map<String, Object> map = new HashMap<>();
        List<Advice> adviceList1 = new ArrayList<>();

            AdviceExample adviceExample = new AdviceExample();
            AdviceExample.Criteria criteria = adviceExample.createCriteria();
            criteria.andReceiverEqualTo(workId);
            criteria.andTitleLike("%" + sousuo + "%");
        adviceExample.setOrderByClause("advice_id DESC");
            PageHelper.startPage(currentPage, perPageCount);
            adviceList1 = adviceService.selectByExample(adviceExample);

        if (adviceList1 != null) {
            map.put("message", "success");
            PageInfo<Advice> pageInfo = new PageInfo<Advice>(adviceList1);
//            employeeService.selectByExample(null);
            map.put("pageCount", pageInfo.getPages());
            map.put("totalNum", pageInfo.getTotal());
            map.put("adviceList1", gson.toJson(adviceList1));
        } else {
            map.put("message", "反馈列表为空");
        }
        return new Gson().toJson(map);
    }
    @ResponseBody
    @RequestMapping(value = "/addEmpAdvice", produces = "text/plain;charset=UTF-8")
    public String addEmpAdvice(
            @RequestParam("workId") String workId,
            @RequestParam("departId") String departId,
            @RequestParam("select") String select,
            @RequestParam("title") String title,
            @RequestParam("content") String content) {
        System.out.println(workId);
        System.out.println(departId);
        System.out.println(select);
        System.out.println(title);
        System.out.println(content);
        Map<String, Object> map = new HashMap<>();
        if (select.equals("经理")) {
            select = departService.selectByPrimaryKey(Integer.parseInt(departId)).getWorkId();
            if (select.equals("1")) {
                map.put("message", "该部门还未设置经理，请联系管理员");
                return new Gson().toJson(map);
            } else {
                Advice advice = new Advice();
                advice.setSender(workId);
                advice.setReceiver(select);
                advice.setTitle(title);
                advice.setContent(content);
                advice.setTime(new Date());
                if (adviceService.insertSelective(advice) == 1) {
                    map.put("message", "建议发送成功");
                    return new Gson().toJson(map);
                } else {
                    map.put("message", "建议发送失败");
                    return new Gson().toJson(map);
                }
            }
        } else {
            Advice advice = new Advice();
            advice.setSender(workId);
            advice.setReceiver(select);
            advice.setTitle(title);
            advice.setContent(content);
            advice.setTime(new Date());
            if (adviceService.insertSelective(advice) == 1) {
                map.put("message", "建议发送成功");
                return new Gson().toJson(map);
            } else {
                map.put("message", "建议发送失败");
                return new Gson().toJson(map);
            }
        }
    }
    @ResponseBody
    @RequestMapping(value = "/addDirectorAdvice", produces = "text/plain;charset=UTF-8")
    public String addDirectorAdvice(
            @RequestParam("workId") String workId,
            @RequestParam("departId") String departId,
            @RequestParam("select") String select,
            @RequestParam("title") String title,
            @RequestParam("content") String content) {
        System.out.println(workId);
        System.out.println(departId);
        System.out.println(select);
        System.out.println(title);
        System.out.println(content);
        Map<String, Object> map = new HashMap<>();
        Advice advice = new Advice();
        advice.setSender(workId);
        advice.setReceiver(select);
        advice.setTitle(title);
        advice.setContent(content);
        advice.setTime(new Date());
        if (adviceService.insertSelective(advice) == 1) {
            map.put("message", "建议发送成功");
            return new Gson().toJson(map);
        } else {
            map.put("message", "建议发送失败");
            return new Gson().toJson(map);
        }
    }
    @ResponseBody
    @RequestMapping(value = "/yiyue", produces = "text/plain;charset=UTF-8")
    public String yiyue(
            @RequestParam("adviceId") String adviceId,
            @RequestParam("result") String result) {
        System.out.println(adviceId);
        System.out.println(result);
        Map<String, Object> map = new HashMap<>();
        Advice advice = new Advice();
        advice.setAdviceId(Integer.parseInt(adviceId));
        advice.setStateId(10);
        if (adviceService.updateByPrimaryKeySelective(advice) == 1) {
            map.put("message", "已阅");
        } else {
            map.put("message", "操作失败");
        }
        return new Gson().toJson(map);
    }


    @ResponseBody
    @RequestMapping(value = "/getContent", produces = "text/plain;charset=UTF-8")
    public String getContent(
            @RequestParam("adviceId") String adviceId) {
        System.out.println(adviceId);
        Map<String, Object> map = new HashMap<>();

        String content=adviceService.selectByPrimaryKey(Integer.parseInt(adviceId)).getContent();
        System.out.println("content"+content);
        map.put("content", content);
        return new Gson().toJson(map);
    }
}

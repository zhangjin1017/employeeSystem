package com.my.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.my.pojo.EmployeeExample;
import com.my.pojo.Notice;
import com.my.pojo.NoticeExample;
import com.my.service.ApplyService;
import com.my.service.DepartService;
import com.my.service.EmployeeService;
import com.my.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/notice")
@CrossOrigin
public class NoticeController {

    Gson gson = new Gson();
    private NoticeService noticeService;
    private ApplyService applyService;
    private DepartService departService;
    private EmployeeService employeeService;

    @Autowired
    public void setNoticeService(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @Autowired
    public void setDepartService(ApplyService applyService) {
        this.applyService = applyService;
    }

    @Autowired
    public void setDepartService(DepartService departService) {
        this.departService = departService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ResponseBody
    @RequestMapping(value = "/getNoticePage", produces = "text/plain;charset=UTF-8")
    public String getNoticePage(
            @RequestParam("sousuo") String sousuo,
            @RequestParam("perPageCount") int perPageCount,
            @RequestParam("currentPage") int currentPage) {
        System.out.println(sousuo);
        System.out.println(perPageCount);
        System.out.println(currentPage);
        Map<String, Object> map = new HashMap<>();
        List<Notice> noticeList = new ArrayList<>();
        if (sousuo.equals("")) {
            NoticeExample noticeExample = new NoticeExample();
            NoticeExample.Criteria criteria = noticeExample.createCriteria();
            criteria.andTitleLike("%" + sousuo + "%");
            noticeExample.setOrderByClause("notice_id DESC");
            PageHelper.startPage(currentPage, perPageCount);
            noticeList = noticeService.selectByExample(noticeExample);
        } else {
            //设置分页条件
            NoticeExample noticeExample = new NoticeExample();
            NoticeExample.Criteria criteria = noticeExample.createCriteria();
            criteria.andTitleLike("%" + sousuo + "%");
            noticeExample.setOrderByClause("notice_id DESC");
            PageHelper.startPage(currentPage, perPageCount);
            noticeList = noticeService.selectByExample(noticeExample);
        }
        if (noticeList != null) {
            map.put("message", "success");
            PageInfo<Notice> pageInfo = new PageInfo<Notice>(noticeList);
//            employeeService.selectByExample(null);
            map.put("pageCount", pageInfo.getPages());
            map.put("totalNum", pageInfo.getTotal());
            map.put("noticeList", gson.toJson(noticeList));

        } else {
            map.put("message", "通知列表为空");
        }
        return new Gson().toJson(map);
    }


    @ResponseBody
    @RequestMapping(value = "/getMyNoticePage", produces = "text/plain;charset=UTF-8")
    public String getMyNoticePage(
            @RequestParam("workId") String workId,
            @RequestParam("sousuo") String sousuo,
            @RequestParam("perPageCount") int perPageCount,
            @RequestParam("currentPage") int currentPage) {
        System.out.println(workId);
        System.out.println(sousuo);
        System.out.println(perPageCount);
        System.out.println(currentPage);
        Map<String, Object> map = new HashMap<>();
        List<Notice> noticeList = new ArrayList<>();

        NoticeExample noticeExample = new NoticeExample();
        NoticeExample.Criteria criteria = noticeExample.createCriteria();
        criteria.andSenderEqualTo(workId);
        criteria.andTitleLike("%" + sousuo + "%");
        noticeExample.setOrderByClause("notice_id DESC");
        PageHelper.startPage(currentPage, perPageCount);
        noticeList = noticeService.selectByExample(noticeExample);


        if (noticeList != null) {
            map.put("message", "success");
            PageInfo<Notice> pageInfo = new PageInfo<Notice>(noticeList);
//            employeeService.selectByExample(null);
            map.put("pageCount", pageInfo.getPages());
            map.put("totalNum", pageInfo.getTotal());
            map.put("noticeList", gson.toJson(noticeList));

        } else {
            map.put("message", "通知列表为空");
        }
        return new Gson().toJson(map);
    }


    @ResponseBody
    @RequestMapping(value = "/getMyNoticePage1", produces = "text/plain;charset=UTF-8")
    public String getMyNoticePage1(
            @RequestParam("workId") String workId,
            @RequestParam("sousuo") String sousuo,
            @RequestParam("perPageCount") int perPageCount,
            @RequestParam("currentPage") int currentPage) {
        System.out.println(workId);
        System.out.println(sousuo);
        System.out.println(perPageCount);
        System.out.println(currentPage);
        Map<String, Object> map = new HashMap<>();
        List<Notice> noticeList1 = new ArrayList<>();
        List<String> list = new ArrayList();

        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andWorkIdEqualTo(workId);
        int departId=employeeService.selectByExample(employeeExample).get(0).getDepartId();
        //作为接收者
        NoticeExample noticeExample1 = new NoticeExample();
        NoticeExample.Criteria criteria1 = noticeExample1.createCriteria();
        list.add(workId);
        list.add("all");
        String dapart = departService.selectByPrimaryKey(departId).getDepart();
        list.add("all" + dapart);
        criteria1.andReceiverIn(list);
        criteria1.andTitleLike("%" + sousuo + "%");
        noticeExample1.setOrderByClause("notice_id DESC");
        PageHelper.startPage(currentPage, perPageCount);
        noticeList1 = noticeService.selectByExample(noticeExample1);

        if (noticeList1 != null) {
            map.put("message", "success");
            PageInfo<Notice> pageInfo = new PageInfo<Notice>(noticeList1);
//            employeeService.selectByExample(null);
            map.put("pageCount", pageInfo.getPages());
            map.put("totalNum", pageInfo.getTotal());
            map.put("noticeList1", gson.toJson(noticeList1));

        } else {
            map.put("message", "通知列表为空");
        }
        return new Gson().toJson(map);
    }

    @ResponseBody
    @RequestMapping(value = "/addDirectorNotice", produces = "text/plain;charset=UTF-8")
    public String addDirectorNotice(
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
        Notice notice = new Notice();
        notice.setSender(workId);
        String dapart = departService.selectByPrimaryKey(Integer.parseInt(departId)).getDepart();
        notice.setReceiver(select + dapart);
        notice.setTitle(title);
        notice.setContent(content);
        notice.setTime(new Date());
        if (noticeService.insertSelective(notice) == 1) {
            map.put("message", "通知发送成功");
            return new Gson().toJson(map);
        } else {
            map.put("message", "通知发送失败");
            return new Gson().toJson(map);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/addManagerNotice", produces = "text/plain;charset=UTF-8")
    public String addManagerNotice(
            @RequestParam("select") String select,
            @RequestParam("title") String title,
            @RequestParam("content") String content) {
        System.out.println(select);
        System.out.println(title);
        System.out.println(content);
        Map<String, Object> map = new HashMap<>();
        if (select.equals("all")) {
            Notice notice = new Notice();
            notice.setSender("00000");
            notice.setReceiver(select);
            notice.setTitle(title);
            notice.setContent(content);
            notice.setTime(new Date());
            if (noticeService.insertSelective(notice) == 1) {
                map.put("message", "通知发送成功");
                return new Gson().toJson(map);
            } else {
                map.put("message", "通知发送失败");
                return new Gson().toJson(map);
            }
        } else {
            String workId = departService.selectByPrimaryKey(Integer.parseInt(select)).getWorkId();
            if (workId.equals("1")) {
                map.put("message", "该部门未设置经理");
                return new Gson().toJson(map);
            } else {
                Notice notice = new Notice();
                notice.setSender("00000");
                notice.setReceiver(workId);
                notice.setTitle(title);
                notice.setContent(content);
                notice.setTime(new Date());
                if (noticeService.insertSelective(notice) == 1) {
                    map.put("message", "通知发送成功");
                    return new Gson().toJson(map);
                } else {
                    map.put("message", "通知发送失败");
                    return new Gson().toJson(map);
                }
            }
        }
    }

    @ResponseBody
    @RequestMapping(value = "/shixiao", produces = "text/plain;charset=UTF-8")
    public String shixiao(
            @RequestParam("noticeId") String noticeId,
            @RequestParam("result") String result) {
        System.out.println(noticeId);
        System.out.println(result);
        ;
        Map<String, Object> map = new HashMap<>();

        Notice notice = new Notice();
        notice.setNoticeId(Integer.parseInt(noticeId));
        notice.setStateId(8);
        if (noticeService.updateByPrimaryKeySelective(notice) == 1) {
            map.put("message", "已失效");
        } else {
            map.put("message", "操作失败");
        }
        return new Gson().toJson(map);
    }

    @ResponseBody
    @RequestMapping(value = "/getContent", produces = "text/plain;charset=UTF-8")
    public String getContent(
            @RequestParam("noticeId") String noticeId) {
        System.out.println(noticeId);
        Map<String, Object> map = new HashMap<>();

        String content=noticeService.selectByPrimaryKey(Integer.parseInt(noticeId)).getContent();
        System.out.println("content"+content);
        map.put("content", content);
        return new Gson().toJson(map);
    }

}
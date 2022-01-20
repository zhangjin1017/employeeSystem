package com.my.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.my.pojo.Apply;
import com.my.pojo.ApplyExample;
import com.my.service.ApplyService;
import com.my.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/apply")
@CrossOrigin
public class ApplyController {
    Gson gson = new Gson();
    private ApplyService applyService;
    private DepartService departService;

    @Autowired
    public void setApplyService(ApplyService applyService) {
        this.applyService = applyService;
    }

    @Autowired
    public void setDepartService(DepartService departService) {
        this.departService = departService;
    }

    /**
     * 管理员获取申请信息
     *
     * @param sousuo
     * @param perPageCount
     * @param currentPage
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getApplyPage", produces = "text/plain;charset=UTF-8")
    public String getApplyPage(
            @RequestParam("sousuo") String sousuo,
            @RequestParam("perPageCount") int perPageCount,
            @RequestParam("currentPage") int currentPage) {
        System.out.println(sousuo);
        System.out.println(perPageCount);
        System.out.println(currentPage);
        Map<String, Object> map = new HashMap<>();
        List<Apply> applyList = new ArrayList<>();
        if (sousuo.equals("")) {
            ApplyExample applyExample = new ApplyExample();
            ApplyExample.Criteria criteria = applyExample.createCriteria();
            criteria.andTitleLike("%" + sousuo + "%");
            applyExample.setOrderByClause("apply_id DESC");
            PageHelper.startPage(currentPage, perPageCount);
            applyList = applyService.selectByExample(applyExample);
        } else {
            //设置分页条件
            ApplyExample applyExample = new ApplyExample();
            ApplyExample.Criteria criteria = applyExample.createCriteria();
            criteria.andTitleLike("%" + sousuo + "%");
            applyExample.setOrderByClause("apply_id DESC");
            PageHelper.startPage(currentPage, perPageCount);
            applyList = applyService.selectByExample(applyExample);
        }
        if (applyList != null) {
            map.put("message", "success");
            PageInfo<Apply> pageInfo = new PageInfo<Apply>(applyList);
//            employeeService.selectByExample(null);
            map.put("pageCount", pageInfo.getPages());
            map.put("totalNum", pageInfo.getTotal());
            map.put("applyList", gson.toJson(applyList));

        } else {
            map.put("message", "申请列表为空");
        }
        return new Gson().toJson(map);
    }

    /**
     * 经理接受的申请
     *
     * @param workId
     * @param sousuo
     * @param perPageCount
     * @param currentPage
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getMyApplyPage", produces = "text/plain;charset=UTF-8")
    public String getMyApplyPage(
            @RequestParam("workId") String workId,
            @RequestParam("sousuo") String sousuo,
            @RequestParam("perPageCount") int perPageCount,
            @RequestParam("currentPage") int currentPage) {
        System.out.println(workId);
        System.out.println(sousuo);
        System.out.println(perPageCount);
        System.out.println(currentPage);
        Map<String, Object> map = new HashMap<>();
        List<Apply> applyList = new ArrayList<>();
        if (sousuo.equals("")) {
            //作为接收者
            System.out.println("作为接收者");
            ApplyExample applyExample1 = new ApplyExample();
            ApplyExample.Criteria criteria1 = applyExample1.createCriteria();
            criteria1.andReceiverEqualTo(workId);
            criteria1.andTitleLike("%" + sousuo + "%");
            applyExample1.setOrderByClause("apply_id DESC");
            PageHelper.startPage(currentPage, perPageCount);
            applyList = applyService.selectByExample(applyExample1);

        } else {
            //作为接收者
            System.out.println("作为接收者");
            ApplyExample applyExample1 = new ApplyExample();
            ApplyExample.Criteria criteria1 = applyExample1.createCriteria();
            criteria1.andReceiverEqualTo(workId);
            criteria1.andTitleLike("%" + sousuo + "%");
            applyExample1.setOrderByClause("apply_id DESC");
            PageHelper.startPage(currentPage, perPageCount);
            applyList = applyService.selectByExample(applyExample1);
        }
        if (applyList != null) {
            map.put("message", "success");
            PageInfo<Apply> pageInfo = new PageInfo<Apply>(applyList);
//            employeeService.selectByExample(null);
            map.put("pageCount", pageInfo.getPages());
            map.put("totalNum", pageInfo.getTotal());
            map.put("applyList", gson.toJson(applyList));
        } else {
            map.put("message", "申请列表为空");
        }
        return new Gson().toJson(map);
    }

    /**
     * 经理发送的申请
     *
     * @param workId
     * @param sousuo
     * @param perPageCount
     * @param currentPage
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getMyApplyPage1", produces = "text/plain;charset=UTF-8")
    public String getMyApplyPage1(
            @RequestParam("workId") String workId,
            @RequestParam("sousuo") String sousuo,
            @RequestParam("perPageCount") int perPageCount,
            @RequestParam("currentPage") int currentPage) {
        System.out.println(workId);
        System.out.println(sousuo);
        System.out.println(perPageCount);
        System.out.println(currentPage);
        Map<String, Object> map = new HashMap<>();
        List<Apply> applyList1 = new ArrayList<>();
        if (sousuo.equals("")) {
            //作为发送者
            System.out.println("作为发送者");
            ApplyExample applyExample = new ApplyExample();
            ApplyExample.Criteria criteria = applyExample.createCriteria();
            criteria.andSenderEqualTo(workId);
            criteria.andTitleLike("%" + sousuo + "%");
            applyExample.setOrderByClause("apply_id DESC");
            PageHelper.startPage(currentPage, perPageCount);
            applyList1 = applyService.selectByExample(applyExample);

        } else {
            //设置分页条件
            System.out.println("作为发送者");
            ApplyExample applyExample = new ApplyExample();
            ApplyExample.Criteria criteria = applyExample.createCriteria();
            criteria.andSenderEqualTo(workId);
            criteria.andTitleLike("%" + sousuo + "%");
            applyExample.setOrderByClause("apply_id DESC");
            PageHelper.startPage(currentPage, perPageCount);
            applyList1 = applyService.selectByExample(applyExample);

        }
        if (applyList1 != null) {
            map.put("message", "success");
            PageInfo<Apply> pageInfo = new PageInfo<Apply>(applyList1);
//            employeeService.selectByExample(null);
            map.put("pageCount", pageInfo.getPages());
            map.put("totalNum", pageInfo.getTotal());
            map.put("applyList1", gson.toJson(applyList1));
        } else {
            map.put("message", "申请列表为空");
        }
        return new Gson().toJson(map);
    }

    /**
     * 员工提交申请
     *
     * @param workId
     * @param departId
     * @param select
     * @param title
     * @param content
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addEmpApply", produces = "text/plain;charset=UTF-8")
    public String addEmpApply(
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
                Apply apply = new Apply();
                apply.setSender(workId);
                apply.setReceiver(select);
                apply.setTitle(title);
                apply.setContent(content);
                apply.setTime(new Date());
                if (applyService.insertSelective(apply) == 1) {
                    map.put("message", "申请发送成功");
                    return new Gson().toJson(map);
                } else {
                    map.put("message", "申请发送失败");
                    return new Gson().toJson(map);
                }
            }
        } else {
            Apply apply = new Apply();
            apply.setSender(workId);
            apply.setReceiver(select);
            apply.setTitle(title);
            apply.setContent(content);
            apply.setTime(new Date());
            if (applyService.insertSelective(apply) == 1) {
                map.put("message", "申请发送成功");
                return new Gson().toJson(map);
            } else {
                map.put("message", "申请发送失败");
                return new Gson().toJson(map);
            }
        }
    }

    @ResponseBody
    @RequestMapping(value = "/tongguo", produces = "text/plain;charset=UTF-8")
    public String tongguo(
            @RequestParam("applyId") String applyId,
            @RequestParam("result") String result) {
        System.out.println(applyId);
        System.out.println(result);
        ;
        Map<String, Object> map = new HashMap<>();

        if (result.equals("1")) {
            Apply apply = new Apply();
            apply.setApplyId(Integer.parseInt(applyId));
            apply.setStateId(3);
            if (applyService.updateByPrimaryKeySelective(apply) == 1) {
                map.put("message", "已通过");
            } else {
                map.put("message", "操作失败");
            }
        }
        if (result.equals("2")) {
            Apply apply = new Apply();
            apply.setApplyId(Integer.parseInt(applyId));
            apply.setStateId(4);
            if (applyService.updateByPrimaryKeySelective(apply) == 1) {
                map.put("message", "已不通过");
            } else {
                map.put("message", "操作失败");
            }
        }
        return new Gson().toJson(map);
    }


    @ResponseBody
    @RequestMapping(value = "/getContent", produces = "text/plain;charset=UTF-8")
    public String getContent(
            @RequestParam("applyId") String applyId) {
        System.out.println(applyId);
        Map<String, Object> map = new HashMap<>();

        String content=applyService.selectByPrimaryKey(Integer.parseInt(applyId)).getContent();
        System.out.println("content"+content);
        map.put("content", content);
        return new Gson().toJson(map);
    }
}

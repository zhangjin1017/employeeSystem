package com.my.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.my.pojo.Depart;
import com.my.pojo.DepartExample;
import com.my.pojo.EmployeeExample;
import com.my.service.DepartService;
import com.my.service.EmployeeService;
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
@RequestMapping("/depart")
@CrossOrigin
public class DepartController {
    Gson gson = new Gson();

    private DepartService departService;
    private EmployeeService employeeService;


    @Autowired
    public void setDepartService(DepartService departService) {
        this.departService = departService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @ResponseBody
    @RequestMapping(value = "/getECharInfor", produces = "text/plain;charset=UTF-8")
    public String getECharInfor() {
        Map<String, Object> map = new HashMap<>();
        List<Depart> list = departService.selectByExample(null);
        List<String> nameList = new ArrayList<>();
        List<Integer> numList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            nameList.add(list.get(i).getDepart());
            numList.add(list.get(i).getNum());
        }
        System.out.println("部门列表" + list);
        System.out.println("名称" + nameList);
        System.out.println("人数" + numList);
        map.put("message", "success");
        map.put("nameList", nameList);
        map.put("numList", numList);
        //map.put("departList", new Gson().toJson(list));
        return new Gson().toJson(map);

    }


    @ResponseBody
    @RequestMapping(value = "/getECharInfor2", produces = "text/plain;charset=UTF-8")
    public String getECharInfor2() {
        Map<String, Object> map = new HashMap<>();
        List<Integer> empNumList = new ArrayList<>();
        List<String> empStateList = new ArrayList<>();
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andStateIdEqualTo(1);
        empNumList.add(employeeService.selectByExample(employeeExample).size());

        EmployeeExample employeeExample1 = new EmployeeExample();
        EmployeeExample.Criteria criteria1 = employeeExample1.createCriteria();
        criteria1.andStateIdEqualTo(2);
        empNumList.add(employeeService.selectByExample(employeeExample1).size());

        EmployeeExample employeeExample2 = new EmployeeExample();
        EmployeeExample.Criteria criteria2 = employeeExample2.createCriteria();
        criteria2.andStateIdEqualTo(4);
        empNumList.add(employeeService.selectByExample(employeeExample2).size());

        EmployeeExample employeeExample3 = new EmployeeExample();
        EmployeeExample.Criteria criteria3 = employeeExample3.createCriteria();
        criteria3.andStateIdEqualTo(6);
        empNumList.add(employeeService.selectByExample(employeeExample3).size());
        System.out.println("人数" + empNumList);
        empStateList.add("在职");
        empStateList.add("待审核");
        empStateList.add("未通过");
        empStateList.add("已离职");
        map.put("message", "success");
        map.put("empNumList", empNumList);
        map.put("empStateList", empStateList);
        return new Gson().toJson(map);
    }

    @ResponseBody
    @RequestMapping(value = "/getAllDepart", produces = "text/plain;charset=UTF-8")
    public String getAllDepart() {
        Map<String, Object> map = new HashMap<>();
        List<Depart> list = departService.selectByExample(null);
        System.out.println("部门列表" + list);
        map.put("message", "success");
            map.put("departList", list);
        return new Gson().toJson(map);
    }

    @ResponseBody
    @RequestMapping(value = "/getDepartPage", produces = "text/plain;charset=UTF-8")
    public String getDepartPage(
            @RequestParam("name") String name,
            @RequestParam("perPageCount") int perPageCount,
            @RequestParam("currentPage") int currentPage) {
        System.out.println(name);
        System.out.println(perPageCount);

        System.out.println(currentPage);
        Map<String, Object> map = new HashMap<>();
        List<Depart> departList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        if (name.equals("")) {
            PageHelper.startPage(currentPage, perPageCount);
            departList = departService.selectByExample(null);
            //查找经理名字
            for (int i = 0; i < departList.size(); i++) {
                if (departList.get(i).getWorkId().equals("1")) {
                    nameList.add("暂无经理");
                } else {
                    EmployeeExample employeeExample = new EmployeeExample();
                    EmployeeExample.Criteria criteria1 = employeeExample.createCriteria();
                    criteria1.andWorkIdEqualTo(departList.get(i).getWorkId());
                    nameList.add(employeeService.selectByExample(employeeExample).
                            get(0).getName());
                }
            }
        } else {
            //设置分页条件
            DepartExample departExample = new DepartExample();
            DepartExample.Criteria criteria = departExample.createCriteria();
            criteria.andDepartLike("%" + name + "%");
            PageHelper.startPage(currentPage, perPageCount);
            departList = departService.selectByExample(departExample);
            //查找经理名字
            for (int i = 0; i < departList.size(); i++) {
                if (departList.get(i).getWorkId().equals("1")) {
                    nameList.add("暂无经理");
                } else {
                    EmployeeExample employeeExample = new EmployeeExample();
                    EmployeeExample.Criteria criteria1 = employeeExample.createCriteria();
                    criteria1.andWorkIdEqualTo(departList.get(i).getWorkId());
                    nameList.add(employeeService.selectByExample(employeeExample).
                            get(0).getName());
                }
                System.out.println("name" + nameList.toString());
            }
        }
        if (departList != null) {
            map.put("message", "success");
            PageInfo<Depart> pageInfo = new PageInfo<Depart>(departList);
            //departService.selectByExample(null);
            map.put("pageCount", pageInfo.getPages());
            map.put("totalNum", pageInfo.getTotal());
            map.put("departList", gson.toJson(departList));
            map.put("nameList", gson.toJson(nameList));
        } else {
            map.put("message", "部门列表为空");
        }
        return new Gson().toJson(map);
    }


    @ResponseBody
    @RequestMapping(value = "/addDepart", produces = "text/plain;charset=UTF-8")
    public String addDepart(
            @RequestParam("departname") String departname) {
        System.out.println(departname);
        Map<String, Object> map = new HashMap<>();
        Depart depart = new Depart();
        depart.setDepart(departname);
        depart.setNum(0);
        depart.setWorkId("1");

        DepartExample departExample = new DepartExample();
        DepartExample.Criteria criteria = departExample.createCriteria();
        criteria.andDepartLike(departname);
        if (departService.selectByExample(departExample).size() == 0) {
            if (departService.insert(depart) == 1) {
                map.put("message", "success");
            } else {
                map.put("message", "error");
            }
        } else {
            map.put("message", "部门已存在");
        }
        return new Gson().toJson(map);
    }

    @ResponseBody
    @RequestMapping(value = "/updateDepart", produces = "text/plain;charset=UTF-8")
    public String updateDepart(
            @RequestParam("departId") String departId,
            @RequestParam("depart") String depart) {
        Map<String, Object> map = new HashMap<>();

        Depart depart1 = new Depart();
        depart1.setDepartId(Integer.parseInt(departId));
        depart1.setDepart(depart);

        DepartExample departExample = new DepartExample();
        DepartExample.Criteria criteria = departExample.createCriteria();
        criteria.andDepartLike(depart);
        if (departService.selectByExample(departExample).size() == 0) {
            if (departService.updateByPrimaryKeySelective(depart1) == 1) {
                map.put("message", "success");
            } else {
                map.put("message", "error");
            }
        } else {
            map.put("message", "该部门已存在");
        }
        return new Gson().toJson(map);
    }


    @ResponseBody
    @RequestMapping(value = "/deleteDepart", produces = "text/plain;charset=UTF-8")
    public String deleteDepart(
            @RequestParam("departId") String departId) {
        Map<String, Object> map = new HashMap<>();

        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andDepartIdEqualTo(Integer.parseInt(departId));
        if (employeeService.selectByExample(employeeExample).size() == 0) {
            if (departService.deleteByPrimaryKey(Integer.parseInt(departId)) == 1) {
                map.put("message", "success");
            } else {
                map.put("message", "error");
            }
        } else {
            map.put("message", "有人正注册此部门，请先审批掉");
        }
        return new Gson().toJson(map);
    }
}

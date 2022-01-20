package com.my.controller;

import com.google.gson.Gson;
import com.my.pojo.Employee;
import com.my.pojo.EmployeeExample;
import com.my.pojo.Manager;
import com.my.pojo.ManagerExample;
import com.my.service.EmployeeService;
import com.my.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

    private ManagerService managerService;
    private EmployeeService employeeService;

    @Autowired
    public void setManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ResponseBody
    @RequestMapping(value = "/loginById", produces = "text/plain;charset=UTF-8")
    public String loginById(@RequestParam("workid") String workid,
                            @RequestParam("password") String password,
                            @RequestParam("lv") String lv) {
        Map<String, String> map = new HashMap<>();
        if (lv.equals("员工")) {
            EmployeeExample employeeExample = new EmployeeExample();
            EmployeeExample.Criteria criteria = employeeExample.createCriteria();
            criteria.andWorkIdEqualTo(workid);
            criteria.andPasswordEqualTo(password);
            List<Employee> list = employeeService.selectByExample(employeeExample);
            if (list.size() == 1) {
                Employee employee = list.get(0);
                if (employee.getStateId() == 1) {
                    map.put("message", "success");
                    map.put("employee", new Gson().toJson(employee));
                    map.put("stateId", Integer.toString(employee.getStateId()));
                    return new Gson().toJson(map);
                } else if (employee.getStateId() == 6 || employee.getStateId() == 4) {
                    map.put("message", "账号状态异常，非本公司员工");
                    return new Gson().toJson(map);
                } else {
                    map.put("message", "账户状态异常，请联系管理员");
                    map.put("employee", new Gson().toJson(employee));
                    map.put("stateId", Integer.toString(employee.getStateId()));
                    return new Gson().toJson(map);
                }

            } else {
                map.put("message", "用户名或密码错误");
                return new Gson().toJson(map);
            }
        }
        if ("管理员".equals(lv)) {
            ManagerExample managerExample = new ManagerExample();
            ManagerExample.Criteria criteria = managerExample.createCriteria();
            criteria.andNameEqualTo(workid);
            criteria.andPasswordEqualTo(password);
            List<Manager> list = managerService.selectByExample(managerExample);
            if (list.size() == 1) {
                Manager manager = list.get(0);
                map.put("message", "success");
                map.put("manager", new Gson().toJson(manager));
                return new Gson().toJson(map);
            } else {
                map.put("message", "用户名或密码错误");
                return new Gson().toJson(map);
            }
        }
        map.put("message", "error");
        return new Gson().toJson(map);
    }
}

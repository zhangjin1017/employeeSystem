package com.my.controller;

import com.google.gson.Gson;
import com.my.pojo.Depart;
import com.my.pojo.Employee;
import com.my.pojo.Kpi;
import com.my.pojo.Record;
import com.my.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manager")
@CrossOrigin
public class ManagerController {

    private ManagerService managerService;
    private EmployeeService employeeService;
    private DepartService departService;
    private RecordService recordService;
    private KpiService kpiService;


    @Autowired
    public void setManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setDepartService(DepartService departService) {
        this.departService = departService;
    }

    @Autowired
    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
    }
    @Autowired
    public void setKpiService(KpiService kpiService) {
        this.kpiService = kpiService;
    }

    @RequestMapping(value = "/getAllEmployee", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getAllEmployee() {
        Map<String, String> map = new HashMap<>();
        List<Employee> list = employeeService.selectByExample(null);
        map.put("message", "success");
        map.put("empList", new Gson().toJson(list));
        return new Gson().toJson(map);
    }

    @RequestMapping(value = "/tingzhi", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String tingzhi(@RequestParam("empId") String empId) {
        Map<String, String> map = new HashMap<>();
        Employee employee = new Employee();
        employee.setEmpId(Integer.parseInt(empId));
        employee.setStateId(5);
        if (employeeService.updateByPrimaryKeySelective(employee) == 1) {
            Record record = new Record();
            record.setP1("00000");
            record.setP2(employeeService.selectByPrimaryKey(Integer.parseInt(empId)).getWorkId());
            record.setContent("状态变更为停职");
            record.setTime(new Date());
            recordService.insertSelective(record);

            map.put("message", "success");
        } else {
            map.put("message", "error");
        }
        return new Gson().toJson(map);
    }

    @RequestMapping(value = "/qingjia", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String qingjia(@RequestParam("empId") String empId) {
        Map<String, String> map = new HashMap<>();
        Employee employee = new Employee();
        employee.setEmpId(Integer.parseInt(empId));
        employee.setStateId(9);
        if (employeeService.updateByPrimaryKeySelective(employee) == 1) {
            Record record = new Record();
            record.setP1("00000");
            record.setP2(employeeService.selectByPrimaryKey(Integer.parseInt(empId)).getWorkId());
            record.setContent("状态变更为请假");
            record.setTime(new Date());
            recordService.insertSelective(record);
            map.put("message", "success");
        } else {
            map.put("message", "error");
        }
        return new Gson().toJson(map);
    }

    @RequestMapping(value = "/huifu", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String huifu(@RequestParam("empId") String empId) {
        Map<String, String> map = new HashMap<>();
        Employee employee = new Employee();
        employee.setEmpId(Integer.parseInt(empId));
        employee.setStateId(1);
        if (employeeService.updateByPrimaryKeySelective(employee) == 1) {
            Record record = new Record();
            record.setP1("00000");
            record.setP2(employeeService.selectByPrimaryKey(Integer.parseInt(empId)).getWorkId());
            record.setContent("恢复在职状态");
            record.setTime(new Date());
            recordService.insertSelective(record);
            map.put("message", "success");
        } else {
            map.put("message", "error");
        }
        return new Gson().toJson(map);
    }

    @RequestMapping(value = "/changePosition", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String changePosition(@RequestParam("empId") String empId,
                                 @RequestParam("num") String num) {
        Map<String, String> map = new HashMap<>();
        String myDirector = departService.selectByPrimaryKey(employeeService.selectByPrimaryKey(Integer.parseInt(empId)).getDepartId()).getWorkId();
        int departId = departService.selectByPrimaryKey(employeeService.selectByPrimaryKey(Integer.parseInt(empId)).getDepartId()).getDepartId();
        String workId = employeeService.selectByPrimaryKey(Integer.parseInt(empId)).getWorkId();
        int kpiId = employeeService.selectByPrimaryKey(Integer.parseInt(empId)).getKpiId();
        if (num.equals("1")) {

            if (myDirector.equals("1")) {
                Employee employee = new Employee();
                employee.setEmpId(Integer.parseInt(empId));
                employee.setPosition("经理");
                if (employeeService.updateByPrimaryKeySelective(employee) == 1) {

                    Depart depart = new Depart();
                    depart.setDepartId(departId);
                    depart.setWorkId(workId);
                    departService.updateByPrimaryKeySelective(depart);

                    //薪资调整
                    Kpi kpi = new Kpi();
                    kpi.setKpiId(kpiId);
                    kpi.setSalary(10000.0);
                    kpiService.updateByPrimaryKeySelective(kpi);

                    Record record = new Record();
                    record.setP1("00000");
                    record.setP2(employeeService.selectByPrimaryKey(Integer.parseInt(empId)).getWorkId());
                    record.setContent("员工升职为经理");
                    record.setTime(new Date());
                    recordService.insertSelective(record);
                    map.put("message", "success");
                } else {
                    map.put("message", "error");
                }
            } else {
                map.put("message", "此部门已有经理！");
            }
        } else if (num.equals("2")) {
            Employee employee = new Employee();
            employee.setEmpId(Integer.parseInt(empId));
            employee.setPosition("员工");
            if (employeeService.updateByPrimaryKeySelective(employee) == 1) {

                Depart depart = new Depart();
                depart.setDepartId(departId);
                depart.setWorkId("1");
                departService.updateByPrimaryKeySelective(depart);
                System.out.println("aaaaaaaaaaaa");
                System.out.println(kpiId);
                //薪资调整
                Kpi kpi = new Kpi();
                kpi.setKpiId(kpiId);
                kpi.setSalary(5000.0);
                kpiService.updateByPrimaryKeySelective(kpi);

                System.out.println("bbbbbbbbbbbb");
                Record record = new Record();
                record.setP1("00000");
                record.setP2(employeeService.selectByPrimaryKey(Integer.parseInt(empId)).getWorkId());
                record.setContent("经理降职为员工");
                record.setTime(new Date());
                recordService.insertSelective(record);
                map.put("message", "success");
            } else {
                map.put("message", "error");
            }
        }
        return new Gson().toJson(map);
    }

    @RequestMapping(value = "/zhuanzhengEmp", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String zhuanzhengEmp(
            @RequestParam("empId") String empId,
            @RequestParam("workId") String workId) {
        Map<String, String> map = new HashMap<>();
        int kpiId = employeeService.selectByPrimaryKey(Integer.parseInt(empId)).getKpiId();
        Employee employee = new Employee();
        employee.setEmpId(Integer.parseInt(empId));
        employee.setPosition("员工");
        if (employeeService.updateByPrimaryKeySelective(employee) == 1) {

            //薪资调整
            Kpi kpi = new Kpi();
            kpi.setKpiId(kpiId);
            kpi.setSalary(5000.0);
            kpiService.updateByPrimaryKeySelective(kpi);

            Record record = new Record();
            record.setP1("00000");
            record.setP2(workId);
            record.setContent("实习生转正");
            record.setTime(new Date());
            recordService.insertSelective(record);
            map.put("message", "转正成功");
        } else {
            System.out.println(1);
            map.put("message", "发生错误,转正失败");
        }
        return new Gson().toJson(map);
    }

    @RequestMapping(value = "/changeDepartByManager", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String changeDepartByManager(@RequestParam("empId") String empId,
                                        @RequestParam("selectDepart") String selectDepart) {
        Map<String, String> map = new HashMap<>();
        Employee employee = employeeService.selectByPrimaryKey(Integer.parseInt(empId));
        String position = employee.getPosition();
        String workId = employee.getWorkId();
        int departId = employee.getDepartId();
        if (position.equals("经理")) {

            if (departService.selectByPrimaryKey(Integer.parseInt(selectDepart)).getWorkId().equals("1")) {
                Employee employee2 = new Employee();
                employee2.setEmpId(Integer.parseInt(empId));
                employee2.setDepartId(Integer.parseInt(selectDepart));
                //原部门信息
                int num = departService.selectByPrimaryKey(departId).getNum();
                Depart depart = new Depart();
                depart.setDepartId(departId);
                depart.setNum(num - 1);
                depart.setWorkId("1");
                //新部门信息
                int num2 = departService.selectByPrimaryKey(Integer.parseInt(selectDepart)).getNum();
                Depart depart2 = new Depart();
                depart2.setDepartId(Integer.parseInt(selectDepart));
                depart2.setNum(num2 + 1);
                depart2.setWorkId(workId);

                if (employeeService.updateByPrimaryKeySelective(employee2) == 1 && departService.updateByPrimaryKeySelective(depart) == 1 && departService.updateByPrimaryKeySelective(depart2) == 1) {
                    Record record = new Record();
                    record.setP1("00000");
                    record.setP2(employeeService.selectByPrimaryKey(Integer.parseInt(empId)).getWorkId());
                    record.setContent("部门调动:" + departId + "→" + selectDepart);
                    record.setTime(new Date());
                    recordService.insertSelective(record);
                    System.out.println("position" + position);
                    System.out.println("selectDepart" + selectDepart);
                    System.out.println("部门调动成功");
                    map.put("message", "success");
                } else {
                    map.put("message", "error");
                }
            } else {
                map.put("message", "此部门已有经理，操作失败");
            }
        } else {
            Employee employee3 = new Employee();
            employee3.setEmpId(Integer.parseInt(empId));
            employee3.setDepartId(Integer.parseInt(selectDepart));

            //原部门信息
            int num = departService.selectByPrimaryKey(departId).getNum();
            Depart depart = new Depart();
            depart.setDepartId(departId);
            depart.setNum(num - 1);
            //新部门信息
            int num2 = departService.selectByPrimaryKey(Integer.parseInt(selectDepart)).getNum();
            Depart depart2 = new Depart();
            depart2.setDepartId(Integer.parseInt(selectDepart));
            depart2.setNum(num2 + 1);

            if (employeeService.updateByPrimaryKeySelective(employee3) == 1 && departService.updateByPrimaryKeySelective(depart) == 1 && departService.updateByPrimaryKeySelective(depart2) == 1) {
                Record record = new Record();
                record.setP1("00000");
                record.setP2(employeeService.selectByPrimaryKey(Integer.parseInt(empId)).getWorkId());
                record.setContent("部门调动:" + departId + "→" + selectDepart);
                record.setTime(new Date());
                recordService.insertSelective(record);
                map.put("message", "success");
            } else {
                map.put("message", "error");
            }
        }
        return new Gson().toJson(map);
    }


}

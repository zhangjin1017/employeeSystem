package com.my.controller;

import com.google.gson.Gson;
import com.my.pojo.Depart;
import com.my.pojo.Employee;
import com.my.pojo.Kpi;
import com.my.pojo.Record;
import com.my.service.DepartService;
import com.my.service.EmployeeService;
import com.my.service.KpiService;
import com.my.service.RecordService;
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
@RequestMapping("/director")
@CrossOrigin
public class DirectorController {
    private EmployeeService employeeService;
    private DepartService departService;
private RecordService recordService;
private KpiService kpiService;

    @Autowired
    public void setDepartService(DepartService departService) {
        this.departService = departService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @Autowired
    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
    }
    @Autowired
    public void setKpiService(KpiService kpiService) {
        this.kpiService = kpiService;
    }

    @RequestMapping(value = "/getDirectorDepart", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getDirectorDepart() {
        Map<String, String> map = new HashMap<>();

        List<Depart> list = departService.selectByExample(null);
        System.out.println("部门列表" + list);
        map.put("message", "success");
        map.put("departList", new Gson().toJson(list));
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
        if(employeeService.updateByPrimaryKeySelective(employee)==1){
            //薪资调整
            Kpi kpi = new Kpi();
            kpi.setKpiId(kpiId);
            kpi.setSalary(5000.0);
            kpiService.updateByPrimaryKeySelective(kpi);

            Record record = new Record();
            record.setP1(departService.selectByPrimaryKey(employeeService.selectByPrimaryKey(Integer.parseInt(empId)).getDepartId()).getWorkId());
            record.setP2(workId);
            record.setContent("实习生转正");
            record.setTime(new Date());
            recordService.insertSelective(record);
            map.put("message", "转正成功");
        }else{
            System.out.println(1);
            map.put("message", "发生错误,转正失败");
        }
        return new Gson().toJson(map);
    }


}

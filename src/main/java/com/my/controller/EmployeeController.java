package com.my.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.my.pojo.*;
import com.my.service.*;
import com.my.util.FaceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


@Controller
@RequestMapping("/employee")
@CrossOrigin
public class EmployeeController {

    Gson gson = new Gson();
    private EmployeeService employeeService;
    private EmpinforService empinforService;
    private AttendanceService attendanceService;
    private DepartService departService;
    private KpiService kpiService;
    private RecordService recordService;

    @Autowired
    public void setAttendanceService(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setEmpinforService(EmpinforService empinforService) {
        this.empinforService = empinforService;
    }

    @Autowired
    public void setDepartService(DepartService departService) {
        this.departService = departService;
    }

    @Autowired
    public void setKpiService(KpiService kpiService) {
        this.kpiService = kpiService;
    }

    @Autowired
    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
    }

    /**
     * 修改密码
     *
     * @param empId
     * @param workId
     * @param password1
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateEmpPassword", produces = "text/plain;charset=UTF-8")
    public String updateEmpPassword(
            @RequestParam("empId") String empId,
            @RequestParam("workId") String workId,
            @RequestParam("password1") String password1) {
        System.out.println(workId);
        System.out.println(password1);
        Map<String, Object> map = new HashMap<>();

        Employee employee = new Employee();
        employee.setEmpId(Integer.parseInt(empId));
        employee.setPassword(password1);
        employeeService.updateByPrimaryKeySelective(employee);

        map.put("message", "success");

        return new Gson().toJson(map);
    }

    /**
     * 员工注册
     *
     * @param workid
     * @param name
     * @param password
     * @param departid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register", produces = "text/plain;charset=UTF-8")
    public String register(@RequestParam("workid") String workid,
                           @RequestParam("name") String name,
                           @RequestParam("password") String password,
                           @RequestParam("departid") String departid) {
        Map<String, String> map = new HashMap<>();
        System.out.println(workid);
        System.out.println(name);
        System.out.println(password);
        System.out.println(departid);
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andWorkIdEqualTo(workid);
        criteria.andPasswordEqualTo(password);
        List<Employee> list = employeeService.selectByExample(employeeExample);
        if (list.size() == 0) {

            //在员工信息表中插入新员工信息
            Empinfor empinfor = new Empinfor();
            empinfor.setWorkId(Integer.parseInt(workid));
            empinfor.setName(name);
            if (empinforService.insert(empinfor) == 1) {
                //在员工表中插入新员工
                Employee employee = new Employee();
                employee.setWorkId(workid);
                employee.setName(name);
                employee.setPassword(password);
                employee.setDepartId(Integer.parseInt(departid));
                employee.setStateId(2);
                //查询infor表中的id
                EmpinforExample empinforExample = new EmpinforExample();
                EmpinforExample.Criteria criteria1 = empinforExample.createCriteria();
                criteria1.andNameEqualTo(name);
                employee.setInforId(empinforService.selectByExample(empinforExample).get(0).getInforId());
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                System.out.println(employee.toString());
                System.out.println(empinfor.toString());
                //判断
                if (employeeService.insert(employee) == 1) {
                    map.put("message", "success");
                    return new Gson().toJson(map);
                } else {
                    map.put("message", "错误，注册失败");
                    return new Gson().toJson(map);
                }
            }
            map.put("message", "错误，注册失败");
            return new Gson().toJson(map);
        } else {
            map.put("message", "员工已存在，请重试！");
            return new Gson().toJson(map);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getpersoninfo", produces = "text/plain;charset=UTF-8")
    public String getpersoninfo(
            @RequestParam("sousuo") String sousuo,
            @RequestParam("perPageCount") int perPageCount,
            @RequestParam("currentPage") int currentPage) {
        System.out.println(sousuo);
        System.out.println(perPageCount);
        System.out.println(currentPage);
        Map<String, Object> map = new HashMap<>();
        List<Empinfor> empinforList = new ArrayList<>();

        EmpinforExample empinforExample = new EmpinforExample();
        EmpinforExample.Criteria criteria = empinforExample.createCriteria();
        criteria.andNameLike("%" + sousuo + "%");
        PageHelper.startPage(currentPage, perPageCount);
        empinforList = empinforService.selectByExample(empinforExample);

        if (empinforList != null) {
            map.put("message", "success");
            PageInfo<Empinfor> pageInfo = new PageInfo<Empinfor>(empinforList);
//            employeeService.selectByExample(null);
            map.put("pageCount", pageInfo.getPages());
            map.put("totalNum", pageInfo.getTotal());
            map.put("personinfoList", gson.toJson(empinforList));

        } else {
            map.put("message", "员工信息列表为空");
        }
        return new Gson().toJson(map);
    }


    @ResponseBody
    @RequestMapping(value = "/resetpassword", produces = "text/plain;charset=UTF-8")
    public String resetpassword(
            @RequestParam("workId") String workId) {
        System.out.println(workId);
        Map<String, Object> map = new HashMap<>();

        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andWorkIdEqualTo(workId);

        Employee employee = new Employee();
        employee.setEmpId(employeeService.selectByExample(employeeExample).get(0).getEmpId());
        employee.setPassword(workId);
        employeeService.updateByPrimaryKeySelective(employee);
        map.put("message", "密码已经重置为员工工号");

        return new Gson().toJson(map);
    }

    @ResponseBody
    @RequestMapping(value = "/leaveComp", produces = "text/plain;charset=UTF-8")
    public String leaveComp(
            @RequestParam("workId") String workId) {
        System.out.println(workId);
        Map<String, Object> map = new HashMap<>();

        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andWorkIdEqualTo(workId);

       int departId=employeeService.selectByExample(employeeExample).get(0).getDepartId();
       String position=employeeService.selectByExample(employeeExample).get(0).getPosition();
//depart修改
       if (position.equals("经理")){
           Depart depart=new Depart();
           depart.setDepartId(departId);
           depart.setWorkId("1");
           depart.setNum(departService.selectByPrimaryKey(departId).getNum()-1);
           departService.updateByPrimaryKeySelective(depart);
       }else{
           Depart depart=new Depart();
           depart.setDepartId(departId);
           depart.setNum(departService.selectByPrimaryKey(departId).getNum()-1);
           departService.updateByPrimaryKeySelective(depart);
       }

try {
    //kpi删除
    kpiService.deleteByPrimaryKey(employeeService.selectByExample(employeeExample).get(0).getKpiId());
    //attendance删除
    AttendanceExample attendanceExample = new AttendanceExample();
    AttendanceExample.Criteria criteria1 = attendanceExample.createCriteria();
    criteria1.andWorkIdEqualTo(Integer.parseInt(workId));
    attendanceService.deleteByPrimaryKey(attendanceService.selectByExample(attendanceExample).get(0).getAttendanceId());
}catch (Exception e){
    map.put("message", "error");
}


        //infor 删除
        empinforService.deleteByPrimaryKey(employeeService.selectByExample(employeeExample).get(0).getInforId());
        //emp更新
        Employee employee = new Employee();
        employee.setEmpId(employeeService.selectByExample(employeeExample).get(0).getEmpId());
        employee.setStateId(6);
        employee.setOuttime(new Date());
        employee.setKpiId(null);
        employee.setInforId(null);
        employeeService.updateByPrimaryKeySelective(employee);
        map.put("message", "success");
        return new Gson().toJson(map);
    }
    
    /**
     * 分页显示员工信息
     *
     * @param sousuo
     * @param perPageCount
     * @param currentPage
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getEmployeePage", produces = "text/plain;charset=UTF-8")
    public String getEmployeePage(
            @RequestParam("sousuo") String sousuo,
            @RequestParam("perPageCount") int perPageCount,
            @RequestParam("currentPage") int currentPage) {
        System.out.println(sousuo);
        System.out.println(perPageCount);
        System.out.println(currentPage);
        Map<String, Object> map = new HashMap<>();
        List<Employee> empList = new ArrayList<>();
        if (sousuo.equals("")) {
            EmployeeExample employeeExample = new EmployeeExample();
            EmployeeExample.Criteria criteria = employeeExample.createCriteria();
            criteria.andPositionIsNotNull();
            criteria.andOuttimeIsNull();
            PageHelper.startPage(currentPage, perPageCount);
            empList = employeeService.selectByExample(employeeExample);
        } else {
            //设置分页条件
            EmployeeExample employeeExample = new EmployeeExample();
            EmployeeExample.Criteria criteria = employeeExample.createCriteria();
            criteria.andPositionIsNotNull();
            criteria.andOuttimeIsNull();
            criteria.andWorkIdLike("%" + sousuo + "%");

            PageHelper.startPage(currentPage, perPageCount);
            empList = employeeService.selectByExample(employeeExample);
        }
        if (empList != null) {
            map.put("message", "success");
            PageInfo<Employee> pageInfo = new PageInfo<Employee>(empList);
//            employeeService.selectByExample(null);
            map.put("pageCount", pageInfo.getPages());
            map.put("totalNum", pageInfo.getTotal());
            map.put("empList", gson.toJson(empList));

        } else {
            map.put("message", "员工列表为空");
        }
        return new Gson().toJson(map);
    }

    /**
     * 分页显示员工信息2
     *
     * @param sousuo
     * @param perPageCount
     * @param currentPage
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getEmployeePage2", produces = "text/plain;charset=UTF-8")
    public String getEmployeePage2(
            @RequestParam("sousuo") String sousuo,
            @RequestParam("perPageCount") int perPageCount,
            @RequestParam("currentPage") int currentPage) {
        System.out.println(sousuo);
        System.out.println(perPageCount);
        System.out.println(currentPage);
        Map<String, Object> map = new HashMap<>();
        List<Employee> empList = new ArrayList<>();
        if (sousuo.equals("")) {
            EmployeeExample employeeExample = new EmployeeExample();
            EmployeeExample.Criteria criteria = employeeExample.createCriteria();
            criteria.andPositionIsNull();
            PageHelper.startPage(currentPage, perPageCount);
            empList = employeeService.selectByExample(employeeExample);
        } else {
            //设置分页条件
            EmployeeExample employeeExample = new EmployeeExample();
            EmployeeExample.Criteria criteria = employeeExample.createCriteria();
            criteria.andPositionIsNull();
            criteria.andWorkIdLike("%" + sousuo + "%");

            PageHelper.startPage(currentPage, perPageCount);
            empList = employeeService.selectByExample(employeeExample);
        }
        if (empList != null) {
            map.put("message", "success");
            PageInfo<Employee> pageInfo = new PageInfo<Employee>(empList);
//            employeeService.selectByExample(null);
            map.put("pageCount", pageInfo.getPages());
            map.put("totalNum", pageInfo.getTotal());
            map.put("empList", gson.toJson(empList));

        } else {
            map.put("message", "员工列表为空");
        }
        return new Gson().toJson(map);
    }

    /**
     * 分页显示员工信息3
     *
     * @param sousuo
     * @param perPageCount
     * @param currentPage
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getEmployeePage3", produces = "text/plain;charset=UTF-8")
    public String getEmployeePage3(
            @RequestParam("sousuo") String sousuo,
            @RequestParam("perPageCount") int perPageCount,
            @RequestParam("currentPage") int currentPage) {
        System.out.println(sousuo);
        System.out.println(perPageCount);
        System.out.println(currentPage);
        Map<String, Object> map = new HashMap<>();
        List<Employee> empList = new ArrayList<>();
        if (sousuo.equals("")) {
            EmployeeExample employeeExample = new EmployeeExample();
            EmployeeExample.Criteria criteria = employeeExample.createCriteria();
            criteria.andOuttimeIsNotNull();
            PageHelper.startPage(currentPage, perPageCount);
            empList = employeeService.selectByExample(employeeExample);
        } else {
            //设置分页条件
            EmployeeExample employeeExample = new EmployeeExample();
            EmployeeExample.Criteria criteria = employeeExample.createCriteria();
            criteria.andOuttimeIsNotNull();
            criteria.andWorkIdLike("%" + sousuo + "%");

            PageHelper.startPage(currentPage, perPageCount);
            empList = employeeService.selectByExample(employeeExample);
        }
        if (empList != null) {
            map.put("message", "success");
            PageInfo<Employee> pageInfo = new PageInfo<Employee>(empList);
//            employeeService.selectByExample(null);
            map.put("pageCount", pageInfo.getPages());
            map.put("totalNum", pageInfo.getTotal());
            map.put("empList", gson.toJson(empList));

        } else {
            map.put("message", "员工列表为空");
        }
        return new Gson().toJson(map);
    }

    /**
     * 分页显示员工的部门信息
     *
     * @param departId
     * @param sousuo
     * @param perPageCount
     * @param currentPage
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getDepartEmployeePage", produces = "text/plain;charset=UTF-8")
    public String getDepartEmployeePage(
            @RequestParam("departId") String departId,
            @RequestParam("sousuo") String sousuo,
            @RequestParam("perPageCount") int perPageCount,
            @RequestParam("currentPage") int currentPage) {
        System.out.println(sousuo);
        System.out.println(perPageCount);
        System.out.println(currentPage);
        Map<String, Object> map = new HashMap<>();
        List<Employee> empList = new ArrayList<>();
        List<String> qqList = new ArrayList<>();
        String myDepart;

        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andPositionIsNotNull();
        criteria.andOuttimeIsNull();
        List list = new ArrayList();
        list.add("员工");
        list.add("实习");
        criteria.andPositionIn(list);
        criteria.andDepartIdEqualTo(Integer.parseInt(departId));
        criteria.andWorkIdLike("%" + sousuo + "%");
        PageHelper.startPage(currentPage, perPageCount);
        empList = employeeService.selectByExample(employeeExample);
        //查找qq
        for (int i = 0; i < empList.size(); i++) {
            EmpinforExample empinforExample = new EmpinforExample();
            EmpinforExample.Criteria criteria1 = empinforExample.createCriteria();
            criteria1.andInforIdEqualTo(empList.get(i).getInforId());
            qqList.add(empinforService.selectByExample(empinforExample).get(0).getQq());
        }
        myDepart = departService.selectByPrimaryKey(Integer.parseInt(departId)).getDepart();

        System.out.println("empList" + empList);
        if (empList.size() >= 1) {
            map.put("message", "success");
            PageInfo<Employee> pageInfo = new PageInfo<Employee>(empList);
//            employeeService.selectByExample(null);
            map.put("pageCount", pageInfo.getPages());
            map.put("totalNum", pageInfo.getTotal());
            map.put("empList", gson.toJson(empList));
            map.put("qqList", gson.toJson(qqList));
            map.put("myDepart", gson.toJson(myDepart));
        } else {
            map.put("message", "员工列表为空");
        }
        return new Gson().toJson(map);
    }


    /**
     * 分页显示员工的部门经理信息
     *
     * @param departId
     * @param sousuo
     * @param perPageCount
     * @param currentPage
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getDepartDirectorPage", produces = "text/plain;charset=UTF-8")
    public String getDepartDirectorPage(
            @RequestParam("departId") String departId,
            @RequestParam("sousuo") String sousuo,
            @RequestParam("perPageCount") int perPageCount,
            @RequestParam("currentPage") int currentPage) {
        System.out.println(sousuo);
        System.out.println(perPageCount);
        System.out.println(currentPage);
        Map<String, Object> map = new HashMap<>();
        List<Employee> directorList = new ArrayList<>();
        List<String> qqList = new ArrayList<>();
        if (sousuo.equals("")) {
            EmployeeExample employeeExample = new EmployeeExample();
            EmployeeExample.Criteria criteria = employeeExample.createCriteria();
            criteria.andPositionIsNotNull();
            criteria.andOuttimeIsNull();
            criteria.andDepartIdEqualTo(Integer.parseInt(departId));
            criteria.andPositionEqualTo("经理");
            PageHelper.startPage(currentPage, perPageCount);
            directorList = employeeService.selectByExample(employeeExample);
            //查找qq
            System.out.println("aaaaaaaaaaaaaa");
            for (int i = 0; i < directorList.size(); i++) {
                EmpinforExample empinforExample = new EmpinforExample();
                EmpinforExample.Criteria criteria1 = empinforExample.createCriteria();
                criteria1.andInforIdEqualTo(directorList.get(i).getInforId());
                qqList.add(empinforService.selectByExample(empinforExample).get(0).getQq());
                System.out.println("qq" + qqList.toString());
            }
            System.out.println("bbbbbbbbbbbbbbb");
        } else {
            //设置分页条件
            EmployeeExample employeeExample = new EmployeeExample();
            EmployeeExample.Criteria criteria = employeeExample.createCriteria();
            criteria.andPositionIsNotNull();
            criteria.andOuttimeIsNull();
            criteria.andDepartIdEqualTo(Integer.parseInt(departId));
            criteria.andPositionEqualTo("经理");
            criteria.andWorkIdLike("%" + sousuo + "%");

            PageHelper.startPage(currentPage, perPageCount);
            directorList = employeeService.selectByExample(employeeExample);
            //查找qq
            System.out.println("aaaaaaaaaaaaaa");
            for (int i = 0; i < directorList.size(); i++) {
                EmpinforExample empinforExample = new EmpinforExample();
                EmpinforExample.Criteria criteria1 = empinforExample.createCriteria();
                criteria1.andInforIdEqualTo(directorList.get(i).getInforId());
                qqList.add(empinforService.selectByExample(empinforExample).get(0).getQq());
                System.out.println("qq" + qqList.toString());
            }
            System.out.println("bbbbbbbbbbbbbbb");
            System.out.println(qqList.size());

        }
        if (directorList.size() >= 1) {
            map.put("message", "success");
            PageInfo<Employee> pageInfo = new PageInfo<Employee>(directorList);
//            employeeService.selectByExample(null);
            map.put("pageCount", pageInfo.getPages());
            map.put("totalNum", pageInfo.getTotal());
            map.put("directorList", gson.toJson(directorList));
            map.put("qqList", gson.toJson(qqList));

        } else {
            map.put("message", "经理列表为空");
        }
        return new Gson().toJson(map);
    }


    /**
     * 获取员工信息
     *
     * @param workId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getEmployeeInfo", produces = "text/plain;charset=UTF-8")
    public String getEmployeeInfo(
            @RequestParam("workId") String workId) {
        System.out.println(workId);
        Map<String, Object> map = new HashMap<>();
        EmpinforExample empinforExample = new EmpinforExample();
        EmpinforExample.Criteria criteria = empinforExample.createCriteria();
        criteria.andWorkIdEqualTo(Integer.parseInt(workId));
        Empinfor empinfor = empinforService.selectByExample(empinforExample).get(0);
        if (empinfor != null) {
            map.put("message", "success");
            map.put("empinfor", empinfor);
        } else {
            map.put("message", "error");
            map.put("message", "出错拉，未查询到员工信息");
        }
        return new Gson().toJson(map);
    }

    /**
     * 修改个人信息
     *
     * @param inforId
     * @param workId
     * @param name
     * @param sex
     * @param age
     * @param phone
     * @param qq
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateEmployeeInfo", produces = "text/plain;charset=UTF-8")
    public String updateEmployeeInfo(
            @RequestParam("inforId") String inforId,
            @RequestParam("workId") String workId,
            @RequestParam("name") String name,
            @RequestParam("sex") String sex,
            @RequestParam("age") String age,
            @RequestParam("phone") String phone,
            @RequestParam("qq") String qq) {
        Map<String, Object> map = new HashMap<>();
        EmpinforExample empinforExample = new EmpinforExample();
        EmpinforExample.Criteria criteria = empinforExample.createCriteria();
        criteria.andInforIdEqualTo(Integer.parseInt(inforId));
        Empinfor empinfor1 = new Empinfor();
        empinfor1.setInforId(Integer.parseInt(inforId));
        empinfor1.setWorkId(Integer.parseInt(workId));
        empinfor1.setName(name);
        empinfor1.setSex(sex);
        empinfor1.setAge(Integer.parseInt(age));
        empinfor1.setPhone(phone);
        empinfor1.setQq(qq);

        if (empinforService.updateByExample(empinfor1, empinforExample) == 1) {
            Empinfor empinfor = empinforService.selectByPrimaryKey(Integer.parseInt(inforId));
            map.put("message", "success");
            map.put("empinfor", empinfor);
        } else {
            map.put("message", "修改失败！");
        }
        return new Gson().toJson(map);
    }

    /**
     * 录入人脸信息
     *
     * @param inforId
     * @param workId
     * @param faceUrl
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateEmployeeFace", produces = "text/plain;charset=UTF-8")
    public String updateEmployeeFace(
            @RequestParam("inforId") String inforId,
            @RequestParam("workId") String workId,
            @RequestParam("faceUrl") String faceUrl) {
        System.out.println("faceUrl" + faceUrl);
        System.out.println("inforId" + inforId);
        System.out.println("workId" + workId);
        Map<String, Object> map = new HashMap<>();
        EmpinforExample empinforExample = new EmpinforExample();
        EmpinforExample.Criteria criteria = empinforExample.createCriteria();
        criteria.andWorkIdEqualTo(Integer.parseInt(workId));
        System.out.println(1);
        if (empinforService.selectByExample(empinforExample).get(0).getFaceUrl() == null) {
            System.out.println(2);
            if (faceUrl.startsWith("data:image/png;base64,")) {
                faceUrl = faceUrl.substring("data:image/png;base64,".length());
            }
            System.out.println(3);
            JSONObject jsonObject = JSONObject.parseObject(FaceUtil.add(faceUrl, workId));
            String message = jsonObject.getString("error_msg");
            System.out.println(message);
            if ("SUCCESS".equals(message)) {
                String faceToken = (String) ((JSONObject) jsonObject.get("result")).get("face_token");
                System.out.println(faceToken);
                Empinfor empinfor1 = new Empinfor();
                empinfor1.setInforId(Integer.parseInt(inforId));
                empinfor1.setWorkId(Integer.parseInt(workId));
                empinfor1.setName(empinforService.selectByExample(empinforExample).get(0).getName());
                empinfor1.setSex(empinforService.selectByExample(empinforExample).get(0).getSex());
                empinfor1.setAge(empinforService.selectByExample(empinforExample).get(0).getAge());
                empinfor1.setPhone(empinforService.selectByExample(empinforExample).get(0).getPhone());
                empinfor1.setQq(empinforService.selectByExample(empinforExample).get(0).getQq());
                empinfor1.setFaceUrl(faceToken);

                if (empinforService.updateByExample(empinfor1, empinforExample) == 1) {
                    //在打卡表中添加此员工
                    Attendance attendance = new Attendance();
                    attendance.setWorkId(Integer.parseInt(workId));
                    attendanceService.insertSelective(attendance);
//                    //获取attendanceid
//                    AttendanceExample attendanceExample=new AttendanceExample();
//                    AttendanceExample.Criteria criteria1 = attendanceExample.createCriteria();
//                    criteria1.andWorkIdEqualTo(Integer.parseInt(workId));
//                    attendanceService.selectByExample(attendanceExample).get(0).getAttendanceId();
                    map.put("message", "success");
                } else {
                    map.put("message", "人脸信息录入失败！");
                }
            } else {
                map.put("message", "人脸已经被注册或信息有误！");
            }
        } else {
            map.put("message", "你已经录入过人脸信息");
        }
        return new Gson().toJson(map);
    }


    @RequestMapping(value = "/dakaByFace", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String dakaByFace(
            @RequestParam("currenttime") String currenttime,
            @RequestParam("inforId") String inforId,
            @RequestParam("workId") String workId,
            @RequestParam("faceUrl") String faceUrl) {
        if (faceUrl.startsWith("data:image/png;base64,")) {
            faceUrl = faceUrl.substring("data:image/png;base64,".length());
        }
        String json = FaceUtil.faceSearch(faceUrl);
        Map<String, String> map = new HashMap<>();
        System.out.println(json);
        JSONObject result = (JSONObject) JSONObject.parseObject(json).get("result");
        if (result != null) {
            JSONObject userList = ((JSONObject) ((JSONArray) result.get("user_list")).get(0));
            String score = userList.getString("score");
            String workId1 = userList.getString("user_info");
            System.out.println("workId1" + workId1);
            System.out.println("Double.parseDouble(score)" + Double.parseDouble(score));
            if (Double.parseDouble(score) > 60) {
                System.out.println("workId1" + workId1);
                System.out.println("workId" + workId);
                if (workId1.equals(workId)) {
                    AttendanceExample attendanceExample = new AttendanceExample();
                    AttendanceExample.Criteria criteria = attendanceExample.createCriteria();
                    criteria.andWorkIdEqualTo(Integer.parseInt(workId));
                    int times = attendanceService.selectByExample(attendanceExample).
                            get(0).getClockTimes();
                    int times2 = attendanceService.selectByExample(attendanceExample).get(0).
                            getRewardTimes();
                    if (times2 != Integer.parseInt(currenttime)) {
                        Attendance attendance = new Attendance();
                        attendance.setClockTimes(times + 1);
                        attendance.setRewardTimes(Integer.parseInt(currenttime));
                        attendanceService.updateByExampleSelective
                                (attendance, attendanceExample);
                        //同步绩效表
                        KpiExample kpiExample = new KpiExample();
                        KpiExample.Criteria criteria1 = kpiExample.createCriteria();
                        criteria1.andWorkIdEqualTo(Integer.parseInt(workId));
                        int kpiId = kpiService.selectByExample(kpiExample).get(0).getKpiId();
                        Kpi kpi = new Kpi();
                        kpi.setKpiId(kpiId);
                        kpi.setNumber(times + 1);
                        kpiService.updateByPrimaryKeySelective(kpi);
                        map.put("message", "success");
                        return new Gson().toJson(map);
                    } else {
                        map.put("message", "今日已打卡");
                        return new Gson().toJson(map);
                    }
                }
            } else {
                map.put("message", "该人脸信息有误,验证分数小于60");
            }
        } else {
            map.put("message", "该人脸未注册或拍摄有误");
        }
        return new Gson().toJson(map);
    }

    @ResponseBody
    @RequestMapping(value = "/inEmployee", produces = "text/plain;charset=UTF-8")
    public String inEmployee(
            @RequestParam("departId") String departId,
            @RequestParam("empId") String empId,
            @RequestParam("workId") String workId,
            @RequestParam("result") String result) {
        Map<String, Object> map = new HashMap<>();

        if (result.equals("1")) {
            Kpi kpi = new Kpi();
            kpi.setWorkId(Integer.parseInt(workId));
            if (kpiService.insertSelective(kpi) == 1) {
                KpiExample kpiExample = new KpiExample();
                KpiExample.Criteria criteria = kpiExample.createCriteria();
                criteria.andWorkIdEqualTo(Integer.parseInt(workId));
                int kpiId = kpiService.selectByExample(kpiExample).get(0).getKpiId();
                System.out.println("kpiId" + kpiId);
                Employee employee = new Employee();
                employee.setEmpId(Integer.parseInt(empId));
                employee.setPosition("实习");
                employee.setStateId(1);
                employee.setIntime(new Date());
                employee.setKpiId(kpiId);
                employeeService.updateByPrimaryKeySelective(employee);

                DepartExample departExample = new DepartExample();
                DepartExample.Criteria criteria1 = departExample.createCriteria();
                criteria1.andDepartIdEqualTo(Integer.parseInt(departId));
                int num = departService.selectByExample(departExample).get(0).getNum();
                Depart depart = new Depart();
                depart.setDepartId(Integer.parseInt(departId));
                depart.setNum(num + 1);
                departService.updateByPrimaryKeySelective(depart);
                map.put("message", "已通过");

                Record record = new Record();
                record.setP1("00000");
                record.setP2(workId);
                record.setContent("入职申请通过");
                record.setTime(new Date());
                recordService.insertSelective(record);
            } else {
                map.put("message", "绩效表出错");
            }
        }
        if (result.equals("2")) {
            Employee employee = new Employee();
            employee.setEmpId(Integer.parseInt(empId));
            employee.setStateId(4);
            employeeService.updateByPrimaryKeySelective(employee);
            map.put("message", "已不通过");
            Record record = new Record();
            record.setP1("00000");
            record.setP2(workId);
            record.setContent("入职申请未通过");
            record.setTime(new Date());
            recordService.insertSelective(record);

        }
        return new Gson().toJson(map);
    }


}



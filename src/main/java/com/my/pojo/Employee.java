package com.my.pojo;

import java.util.Date;

public class Employee {
    private Integer empId;

    private String workId;

    private String name;

    private String password;

    private Integer departId;

    private String position;

    private Integer stateId;

    private Date intime;

    private Date outtime;

    private Integer kpiId;

    private Integer inforId;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId == null ? null : workId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Date getIntime() {
        return intime;
    }

    public void setIntime(Date intime) {
        this.intime = intime;
    }

    public Date getOuttime() {
        return outtime;
    }

    public void setOuttime(Date outtime) {
        this.outtime = outtime;
    }

    public Integer getKpiId() {
        return kpiId;
    }

    public void setKpiId(Integer kpiId) {
        this.kpiId = kpiId;
    }

    public Integer getInforId() {
        return inforId;
    }

    public void setInforId(Integer inforId) {
        this.inforId = inforId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", workId='" + workId + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", departId=" + departId +
                ", position='" + position + '\'' +
                ", stateId=" + stateId +
                ", intime=" + intime +
                ", outtime=" + outtime +
                ", kpiId=" + kpiId +
                ", inforId=" + inforId +
                '}';
    }
}
package com.my.pojo;

import java.io.Serializable;

public class Empinfor implements Serializable {
    private Integer inforId;

    private Integer workId;

    private String name;

    private String sex;

    private Integer age;

    private String phone;

    private String qq;

    private String faceUrl;

    public Integer getInforId() {
        return inforId;
    }

    public void setInforId(Integer inforId) {
        this.inforId = inforId;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl == null ? null : faceUrl.trim();
    }

    @Override
    public String toString() {
        return "Empinfor{" +
                "inforId=" + inforId +
                ", workId=" + workId +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", qq='" + qq + '\'' +
                ", faceUrl='" + faceUrl + '\'' +
                '}';
    }
}
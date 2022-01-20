package com.my.pojo;

public class Depart {
    private Integer departId;

    private String depart;

    private Integer num;

    private String workId;

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart == null ? null : depart.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId == null ? null : workId.trim();
    }

    @Override
    public String toString() {
        return "Depart{" +
                "departId=" + departId +
                ", depart='" + depart + '\'' +
                ", num=" + num +
                ", workId='" + workId + '\'' +
                '}';
    }
}
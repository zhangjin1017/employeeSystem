package com.my.pojo;

import java.io.Serializable;

public class Attendance implements Serializable {
    private Integer attendanceId;

    private Integer workId;

    private Integer clockTimes;

    private Integer rewardTimes;

    private Integer punishTimes;

    public Integer getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Integer attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public Integer getClockTimes() {
        return clockTimes;
    }

    public void setClockTimes(Integer clockTimes) {
        this.clockTimes = clockTimes;
    }

    public Integer getRewardTimes() {
        return rewardTimes;
    }

    public void setRewardTimes(Integer rewardTimes) {
        this.rewardTimes = rewardTimes;
    }

    public Integer getPunishTimes() {
        return punishTimes;
    }

    public void setPunishTimes(Integer punishTimes) {
        this.punishTimes = punishTimes;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "attendanceId=" + attendanceId +
                ", workId=" + workId +
                ", clockTimes=" + clockTimes +
                ", rewardTimes=" + rewardTimes +
                ", punishTimes=" + punishTimes +
                '}';
    }
}
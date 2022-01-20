package com.my.pojo;

import java.util.ArrayList;
import java.util.List;

public class AttendanceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AttendanceExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andAttendanceIdIsNull() {
            addCriterion("attendance_id is null");
            return (Criteria) this;
        }

        public Criteria andAttendanceIdIsNotNull() {
            addCriterion("attendance_id is not null");
            return (Criteria) this;
        }

        public Criteria andAttendanceIdEqualTo(Integer value) {
            addCriterion("attendance_id =", value, "attendanceId");
            return (Criteria) this;
        }

        public Criteria andAttendanceIdNotEqualTo(Integer value) {
            addCriterion("attendance_id <>", value, "attendanceId");
            return (Criteria) this;
        }

        public Criteria andAttendanceIdGreaterThan(Integer value) {
            addCriterion("attendance_id >", value, "attendanceId");
            return (Criteria) this;
        }

        public Criteria andAttendanceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("attendance_id >=", value, "attendanceId");
            return (Criteria) this;
        }

        public Criteria andAttendanceIdLessThan(Integer value) {
            addCriterion("attendance_id <", value, "attendanceId");
            return (Criteria) this;
        }

        public Criteria andAttendanceIdLessThanOrEqualTo(Integer value) {
            addCriterion("attendance_id <=", value, "attendanceId");
            return (Criteria) this;
        }

        public Criteria andAttendanceIdIn(List<Integer> values) {
            addCriterion("attendance_id in", values, "attendanceId");
            return (Criteria) this;
        }

        public Criteria andAttendanceIdNotIn(List<Integer> values) {
            addCriterion("attendance_id not in", values, "attendanceId");
            return (Criteria) this;
        }

        public Criteria andAttendanceIdBetween(Integer value1, Integer value2) {
            addCriterion("attendance_id between", value1, value2, "attendanceId");
            return (Criteria) this;
        }

        public Criteria andAttendanceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("attendance_id not between", value1, value2, "attendanceId");
            return (Criteria) this;
        }

        public Criteria andWorkIdIsNull() {
            addCriterion("work_id is null");
            return (Criteria) this;
        }

        public Criteria andWorkIdIsNotNull() {
            addCriterion("work_id is not null");
            return (Criteria) this;
        }

        public Criteria andWorkIdEqualTo(Integer value) {
            addCriterion("work_id =", value, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdNotEqualTo(Integer value) {
            addCriterion("work_id <>", value, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdGreaterThan(Integer value) {
            addCriterion("work_id >", value, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("work_id >=", value, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdLessThan(Integer value) {
            addCriterion("work_id <", value, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdLessThanOrEqualTo(Integer value) {
            addCriterion("work_id <=", value, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdIn(List<Integer> values) {
            addCriterion("work_id in", values, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdNotIn(List<Integer> values) {
            addCriterion("work_id not in", values, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdBetween(Integer value1, Integer value2) {
            addCriterion("work_id between", value1, value2, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdNotBetween(Integer value1, Integer value2) {
            addCriterion("work_id not between", value1, value2, "workId");
            return (Criteria) this;
        }

        public Criteria andClockTimesIsNull() {
            addCriterion("clock_times is null");
            return (Criteria) this;
        }

        public Criteria andClockTimesIsNotNull() {
            addCriterion("clock_times is not null");
            return (Criteria) this;
        }

        public Criteria andClockTimesEqualTo(Integer value) {
            addCriterion("clock_times =", value, "clockTimes");
            return (Criteria) this;
        }

        public Criteria andClockTimesNotEqualTo(Integer value) {
            addCriterion("clock_times <>", value, "clockTimes");
            return (Criteria) this;
        }

        public Criteria andClockTimesGreaterThan(Integer value) {
            addCriterion("clock_times >", value, "clockTimes");
            return (Criteria) this;
        }

        public Criteria andClockTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("clock_times >=", value, "clockTimes");
            return (Criteria) this;
        }

        public Criteria andClockTimesLessThan(Integer value) {
            addCriterion("clock_times <", value, "clockTimes");
            return (Criteria) this;
        }

        public Criteria andClockTimesLessThanOrEqualTo(Integer value) {
            addCriterion("clock_times <=", value, "clockTimes");
            return (Criteria) this;
        }

        public Criteria andClockTimesIn(List<Integer> values) {
            addCriterion("clock_times in", values, "clockTimes");
            return (Criteria) this;
        }

        public Criteria andClockTimesNotIn(List<Integer> values) {
            addCriterion("clock_times not in", values, "clockTimes");
            return (Criteria) this;
        }

        public Criteria andClockTimesBetween(Integer value1, Integer value2) {
            addCriterion("clock_times between", value1, value2, "clockTimes");
            return (Criteria) this;
        }

        public Criteria andClockTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("clock_times not between", value1, value2, "clockTimes");
            return (Criteria) this;
        }

        public Criteria andRewardTimesIsNull() {
            addCriterion("reward_times is null");
            return (Criteria) this;
        }

        public Criteria andRewardTimesIsNotNull() {
            addCriterion("reward_times is not null");
            return (Criteria) this;
        }

        public Criteria andRewardTimesEqualTo(Integer value) {
            addCriterion("reward_times =", value, "rewardTimes");
            return (Criteria) this;
        }

        public Criteria andRewardTimesNotEqualTo(Integer value) {
            addCriterion("reward_times <>", value, "rewardTimes");
            return (Criteria) this;
        }

        public Criteria andRewardTimesGreaterThan(Integer value) {
            addCriterion("reward_times >", value, "rewardTimes");
            return (Criteria) this;
        }

        public Criteria andRewardTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("reward_times >=", value, "rewardTimes");
            return (Criteria) this;
        }

        public Criteria andRewardTimesLessThan(Integer value) {
            addCriterion("reward_times <", value, "rewardTimes");
            return (Criteria) this;
        }

        public Criteria andRewardTimesLessThanOrEqualTo(Integer value) {
            addCriterion("reward_times <=", value, "rewardTimes");
            return (Criteria) this;
        }

        public Criteria andRewardTimesIn(List<Integer> values) {
            addCriterion("reward_times in", values, "rewardTimes");
            return (Criteria) this;
        }

        public Criteria andRewardTimesNotIn(List<Integer> values) {
            addCriterion("reward_times not in", values, "rewardTimes");
            return (Criteria) this;
        }

        public Criteria andRewardTimesBetween(Integer value1, Integer value2) {
            addCriterion("reward_times between", value1, value2, "rewardTimes");
            return (Criteria) this;
        }

        public Criteria andRewardTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("reward_times not between", value1, value2, "rewardTimes");
            return (Criteria) this;
        }

        public Criteria andPunishTimesIsNull() {
            addCriterion("punish_times is null");
            return (Criteria) this;
        }

        public Criteria andPunishTimesIsNotNull() {
            addCriterion("punish_times is not null");
            return (Criteria) this;
        }

        public Criteria andPunishTimesEqualTo(Integer value) {
            addCriterion("punish_times =", value, "punishTimes");
            return (Criteria) this;
        }

        public Criteria andPunishTimesNotEqualTo(Integer value) {
            addCriterion("punish_times <>", value, "punishTimes");
            return (Criteria) this;
        }

        public Criteria andPunishTimesGreaterThan(Integer value) {
            addCriterion("punish_times >", value, "punishTimes");
            return (Criteria) this;
        }

        public Criteria andPunishTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("punish_times >=", value, "punishTimes");
            return (Criteria) this;
        }

        public Criteria andPunishTimesLessThan(Integer value) {
            addCriterion("punish_times <", value, "punishTimes");
            return (Criteria) this;
        }

        public Criteria andPunishTimesLessThanOrEqualTo(Integer value) {
            addCriterion("punish_times <=", value, "punishTimes");
            return (Criteria) this;
        }

        public Criteria andPunishTimesIn(List<Integer> values) {
            addCriterion("punish_times in", values, "punishTimes");
            return (Criteria) this;
        }

        public Criteria andPunishTimesNotIn(List<Integer> values) {
            addCriterion("punish_times not in", values, "punishTimes");
            return (Criteria) this;
        }

        public Criteria andPunishTimesBetween(Integer value1, Integer value2) {
            addCriterion("punish_times between", value1, value2, "punishTimes");
            return (Criteria) this;
        }

        public Criteria andPunishTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("punish_times not between", value1, value2, "punishTimes");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
package com.my.pojo;

import java.util.ArrayList;
import java.util.List;

public class DepartExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DepartExample() {
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

        public Criteria andDepartIdIsNull() {
            addCriterion("depart_id is null");
            return (Criteria) this;
        }

        public Criteria andDepartIdIsNotNull() {
            addCriterion("depart_id is not null");
            return (Criteria) this;
        }

        public Criteria andDepartIdEqualTo(Integer value) {
            addCriterion("depart_id =", value, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdNotEqualTo(Integer value) {
            addCriterion("depart_id <>", value, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdGreaterThan(Integer value) {
            addCriterion("depart_id >", value, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("depart_id >=", value, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdLessThan(Integer value) {
            addCriterion("depart_id <", value, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdLessThanOrEqualTo(Integer value) {
            addCriterion("depart_id <=", value, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdIn(List<Integer> values) {
            addCriterion("depart_id in", values, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdNotIn(List<Integer> values) {
            addCriterion("depart_id not in", values, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdBetween(Integer value1, Integer value2) {
            addCriterion("depart_id between", value1, value2, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdNotBetween(Integer value1, Integer value2) {
            addCriterion("depart_id not between", value1, value2, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIsNull() {
            addCriterion("depart is null");
            return (Criteria) this;
        }

        public Criteria andDepartIsNotNull() {
            addCriterion("depart is not null");
            return (Criteria) this;
        }

        public Criteria andDepartEqualTo(String value) {
            addCriterion("depart =", value, "depart");
            return (Criteria) this;
        }

        public Criteria andDepartNotEqualTo(String value) {
            addCriterion("depart <>", value, "depart");
            return (Criteria) this;
        }

        public Criteria andDepartGreaterThan(String value) {
            addCriterion("depart >", value, "depart");
            return (Criteria) this;
        }

        public Criteria andDepartGreaterThanOrEqualTo(String value) {
            addCriterion("depart >=", value, "depart");
            return (Criteria) this;
        }

        public Criteria andDepartLessThan(String value) {
            addCriterion("depart <", value, "depart");
            return (Criteria) this;
        }

        public Criteria andDepartLessThanOrEqualTo(String value) {
            addCriterion("depart <=", value, "depart");
            return (Criteria) this;
        }

        public Criteria andDepartLike(String value) {
            addCriterion("depart like", value, "depart");
            return (Criteria) this;
        }

        public Criteria andDepartNotLike(String value) {
            addCriterion("depart not like", value, "depart");
            return (Criteria) this;
        }

        public Criteria andDepartIn(List<String> values) {
            addCriterion("depart in", values, "depart");
            return (Criteria) this;
        }

        public Criteria andDepartNotIn(List<String> values) {
            addCriterion("depart not in", values, "depart");
            return (Criteria) this;
        }

        public Criteria andDepartBetween(String value1, String value2) {
            addCriterion("depart between", value1, value2, "depart");
            return (Criteria) this;
        }

        public Criteria andDepartNotBetween(String value1, String value2) {
            addCriterion("depart not between", value1, value2, "depart");
            return (Criteria) this;
        }

        public Criteria andNumIsNull() {
            addCriterion("num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(Integer value) {
            addCriterion("num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(Integer value) {
            addCriterion("num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(Integer value) {
            addCriterion("num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(Integer value) {
            addCriterion("num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(Integer value) {
            addCriterion("num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<Integer> values) {
            addCriterion("num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<Integer> values) {
            addCriterion("num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(Integer value1, Integer value2) {
            addCriterion("num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(Integer value1, Integer value2) {
            addCriterion("num not between", value1, value2, "num");
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

        public Criteria andWorkIdEqualTo(String value) {
            addCriterion("work_id =", value, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdNotEqualTo(String value) {
            addCriterion("work_id <>", value, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdGreaterThan(String value) {
            addCriterion("work_id >", value, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdGreaterThanOrEqualTo(String value) {
            addCriterion("work_id >=", value, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdLessThan(String value) {
            addCriterion("work_id <", value, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdLessThanOrEqualTo(String value) {
            addCriterion("work_id <=", value, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdLike(String value) {
            addCriterion("work_id like", value, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdNotLike(String value) {
            addCriterion("work_id not like", value, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdIn(List<String> values) {
            addCriterion("work_id in", values, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdNotIn(List<String> values) {
            addCriterion("work_id not in", values, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdBetween(String value1, String value2) {
            addCriterion("work_id between", value1, value2, "workId");
            return (Criteria) this;
        }

        public Criteria andWorkIdNotBetween(String value1, String value2) {
            addCriterion("work_id not between", value1, value2, "workId");
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
package com.qiqi.rs.admin.platform.employee.model;

import com.qiqi.rs.admin.platform.base.QueryBase;

import java.util.Date;
import java.util.Map;

public class EmployeeQuery extends QueryBase {

    private Long id;
    private Long deptId;

    private Integer state;

    private Date joinDateS;
    private Date joinDateE;

    private String month;

    private Map<String, EmployeeQueryParam> othersQueryMap;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Map<String, EmployeeQueryParam> getOthersQueryMap() {
        return othersQueryMap;
    }

    public void setOthersQueryMap(Map<String, EmployeeQueryParam> othersQueryMap) {
        this.othersQueryMap = othersQueryMap;
    }

    public Date getJoinDateS() {
        return joinDateS;
    }

    public void setJoinDateS(Date joinDateS) {
        this.joinDateS = joinDateS;
    }

    public Date getJoinDateE() {
        return joinDateE;
    }

    public void setJoinDateE(Date joinDateE) {
        this.joinDateE = joinDateE;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}

package com.qiqi.rs.admin.platform.salary.model;

import com.qiqi.rs.admin.platform.base.PlatformBase;

public class Salary extends PlatformBase {
    private Long employeeId;
    private String month;
    private Double salary;
    private Long deptId;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
}

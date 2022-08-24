package com.qiqi.rs.admin.platform.salary.model;

public class EmployeeSalaryItem extends SalaryItem {
    private Long employeeId;
    private Long salaryItemId;
    private Integer count;
    private Double price;
    private Double money;

    private Long salaryId;
    private String month;

    public Long getSalaryItemId() {
        return salaryItemId;
    }

    public void setSalaryItemId(Long salaryItemId) {
        this.salaryItemId = salaryItemId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Long getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(Long salaryId) {
        this.salaryId = salaryId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}

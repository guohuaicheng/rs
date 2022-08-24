package com.qiqi.rs.admin.platform.employee.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.qiqi.rs.admin.platform.dept.model.DeptDTO;
import com.qiqi.rs.admin.platform.salary.model.SalaryItemDTO;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDTO extends Employee {

//    private String deptName;
    private DeptDTO dept;
    private DeptDTO parentDept;

    public DeptDTO getDept() {
        return dept;
    }

    public DeptDTO getParentDept() {
        return parentDept;
    }

    private List<SalaryItemDTO> salaryItemList;

    public List<SalaryItemDTO> getSalaryItemList() {
        return salaryItemList;
    }

    public void setSalaryItemList(List<SalaryItemDTO> salaryItemList) {
        this.salaryItemList = salaryItemList;
    }
}

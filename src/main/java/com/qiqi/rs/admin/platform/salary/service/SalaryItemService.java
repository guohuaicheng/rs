package com.qiqi.rs.admin.platform.salary.service;

import com.qiqi.rs.admin.platform.employee.model.EmployeeQuery;
import com.qiqi.rs.admin.platform.salary.model.EmployeeSalaryItem;
import com.qiqi.rs.admin.platform.salary.model.SalaryItem;

import java.util.List;

public interface SalaryItemService {

    SalaryItem create(SalaryItem postSalaryItem);

    SalaryItem update(SalaryItem postSalaryItem);

    SalaryItem delete(SalaryItem postSalaryItem);

    List<SalaryItem> query();

    EmployeeSalaryItem bindToEmployee(EmployeeSalaryItem postEmployeeSalaryItem);

    EmployeeSalaryItem updateToEmployee(EmployeeSalaryItem postEmployeeSalaryItem);

    EmployeeSalaryItem deleteToEmployee(EmployeeSalaryItem postEmployeeSalaryItem);

    EmployeeSalaryItem resetToEmployee(EmployeeQuery employeeQuery);
}

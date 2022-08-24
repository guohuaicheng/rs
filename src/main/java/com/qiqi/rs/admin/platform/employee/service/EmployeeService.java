package com.qiqi.rs.admin.platform.employee.service;

import com.qiqi.core.model.Result;
import com.qiqi.core.utils.PageCondition;
import com.qiqi.rs.admin.platform.employee.model.EmployeeDTO;
import com.qiqi.rs.admin.platform.employee.model.EmployeeQuery;

import java.util.List;

public interface EmployeeService {
    Result<List<EmployeeDTO>> query(EmployeeQuery employeeQuery, PageCondition pageCondition);

    Result<List<EmployeeDTO>> queryWithSalaryItem(EmployeeQuery employeeQuery, PageCondition pageCondition);

    Result<List<EmployeeDTO>> queryWithSalary(EmployeeQuery employeeQuery, PageCondition pageCondition);
}

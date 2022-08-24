package com.qiqi.rs.admin.platform.employee.service;

import com.qiqi.rs.admin.platform.employee.model.EmployeeDTO;

public interface EmployeeOptService {
    EmployeeDTO create(EmployeeDTO postEmployee);

    EmployeeDTO join(EmployeeDTO postEmployee);

    EmployeeDTO leave(EmployeeDTO postEmployee);

    EmployeeDTO update(EmployeeDTO postEmployee);
}

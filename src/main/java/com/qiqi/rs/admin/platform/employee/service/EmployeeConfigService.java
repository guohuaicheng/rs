package com.qiqi.rs.admin.platform.employee.service;

import com.qiqi.rs.admin.platform.employee.model.EmployeeConfig;
import com.qiqi.rs.admin.platform.employee.model.EmployeeConfigDTO;
import com.qiqi.rs.admin.platform.employee.model.EmployeeConfigQuery;

import java.util.List;

public interface EmployeeConfigService {
    EmployeeConfigDTO create(EmployeeConfig postEmployeeConfig);

    EmployeeConfigDTO modify(EmployeeConfig postEmployeeConfig);

    EmployeeConfigDTO delete(EmployeeConfig postEmployeeConfig);

    List<EmployeeConfigDTO> query(EmployeeConfigQuery employeeConfigQuery);
}

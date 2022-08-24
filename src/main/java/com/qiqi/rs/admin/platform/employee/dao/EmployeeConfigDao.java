package com.qiqi.rs.admin.platform.employee.dao;

import com.qiqi.rs.admin.platform.employee.model.EmployeeConfig;
import com.qiqi.rs.admin.platform.employee.model.EmployeeConfigDTO;
import com.qiqi.rs.admin.platform.employee.model.EmployeeConfigQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeConfigDao {
    int insert(EmployeeConfig employeeConfig);

    int update(EmployeeConfig employeeConfig);

    int delete(EmployeeConfig employeeConfig);

    List<EmployeeConfigDTO> select(EmployeeConfigQuery employeeConfigQuery);

    Long selectMaxId();
}

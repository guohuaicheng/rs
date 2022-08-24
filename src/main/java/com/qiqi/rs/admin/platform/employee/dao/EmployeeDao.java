package com.qiqi.rs.admin.platform.employee.dao;

import com.qiqi.rs.admin.platform.employee.model.Employee;
import com.qiqi.rs.admin.platform.employee.model.EmployeeDTO;
import com.qiqi.rs.admin.platform.employee.model.EmployeeQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeDao {
    int insert(Employee employee);

    int update(Employee employee);

    int updateJoin(Employee employee);

    int updateLeave(Employee employee);

    int deleteById(@Param("id") long id);

    List<EmployeeDTO> selectByDeptId(@Param("deptId") long deptId);

    List<EmployeeDTO> select(EmployeeQuery employeeQuery);

    List<EmployeeDTO> selectWithSalaryItem(EmployeeQuery employeeQuery);

    List<EmployeeDTO> selectWithSalary(EmployeeQuery employeeQuery);


    EmployeeDTO selectByIdForOpt(@Param("id") long id);
}

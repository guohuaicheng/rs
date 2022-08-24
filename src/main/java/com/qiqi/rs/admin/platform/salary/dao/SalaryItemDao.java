package com.qiqi.rs.admin.platform.salary.dao;

import com.qiqi.rs.admin.platform.employee.model.EmployeeQuery;
import com.qiqi.rs.admin.platform.salary.model.EmployeeSalaryItem;
import com.qiqi.rs.admin.platform.salary.model.SalaryItem;
import com.qiqi.rs.admin.platform.salary.model.SalaryItemDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SalaryItemDao {

    int insert(SalaryItem salaryItem);

    int update(SalaryItem salaryItem);

    int delete(SalaryItem salaryItem);

    SalaryItem selectById(@Param("id") long id);

    List<SalaryItem> select();

    List<SalaryItem> selectBySalaryItemId(@Param("salaryItemId") long salaryItemId);

    List<SalaryItemDTO> selectByEmployeeId(@Param("employeeId") long employeeId);

    List<SalaryItemDTO> selectFixedByEmployeeIdAndMonth(@Param("employeeId") long employeeId);

    int insertEmployeeSalaryItem(EmployeeSalaryItem employeeSalaryItem);

    int updateEmployeeSalaryItem(EmployeeSalaryItem employeeSalaryItem);

    int deleteEmployeeSalaryItem(EmployeeSalaryItem employeeSalaryItem);

    int resetEmployeeSalaryItem(EmployeeQuery employeeQuery);

    SalaryItemDTO selectByEmployeeIdAndSalaryItemId(@Param("employeeId") long employeeId, @Param("salaryItemId") long salaryItemId);
}

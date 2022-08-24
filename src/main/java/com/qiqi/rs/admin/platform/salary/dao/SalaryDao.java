package com.qiqi.rs.admin.platform.salary.dao;

import com.qiqi.rs.admin.platform.salary.model.EmployeeSalaryItem;
import com.qiqi.rs.admin.platform.salary.model.Salary;
import com.qiqi.rs.admin.platform.salary.model.SalaryDTO;
import com.qiqi.rs.admin.platform.salary.model.SalaryQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SalaryDao {
    int insertList(@Param("salaryList") List<Salary> salaryList);

    int delete(SalaryQuery salaryQuery);

    List<SalaryDTO> select(SalaryQuery salaryQuery);


    int insertEmployeeSalaryItemList(@Param("itemList") List<EmployeeSalaryItem> itemList);

    int deleteEmployeeSalaryItem(SalaryQuery salaryQuery);
}

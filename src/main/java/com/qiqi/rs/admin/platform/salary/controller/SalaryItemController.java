package com.qiqi.rs.admin.platform.salary.controller;

import com.qiqi.core.model.Result;
import com.qiqi.rs.admin.platform.employee.model.EmployeeQuery;
import com.qiqi.rs.admin.platform.salary.model.EmployeeSalaryItem;
import com.qiqi.rs.admin.platform.salary.model.SalaryItem;
import com.qiqi.rs.admin.platform.salary.service.SalaryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SalaryItemController {

    @Autowired
    private SalaryItemService salaryItemService;

    @RequestMapping(value = "/salary-item", method = RequestMethod.POST)
    public Result<SalaryItem> create(@RequestBody SalaryItem postSalaryItem) {
        return new Result<>(salaryItemService.create(postSalaryItem));
    }

    @RequestMapping(value = "/salary-item", method = RequestMethod.PATCH)
    public Result<SalaryItem> update(@RequestBody SalaryItem postSalaryItem) {
        return new Result<>(salaryItemService.update(postSalaryItem));
    }

    @RequestMapping(value = "/salary-item", method = RequestMethod.DELETE)
    public Result<SalaryItem> delete(@RequestBody SalaryItem postSalaryItem) {
        return new Result<>(salaryItemService.delete(postSalaryItem));
    }

    @RequestMapping(value = "/salary-item", method = RequestMethod.GET)
    public Result<List<SalaryItem>> query() {
        return new Result<>(salaryItemService.query());
    }

    @RequestMapping(value = "/employee-salary-item", method = RequestMethod.POST)
    public Result<EmployeeSalaryItem> bindToEmployee(@RequestBody EmployeeSalaryItem postEmployeeSalaryItem) {
        return new Result<>(salaryItemService.bindToEmployee(postEmployeeSalaryItem));
    }

    @RequestMapping(value = "/employee-salary-item", method = RequestMethod.PATCH)
    public Result<EmployeeSalaryItem> updateToEmployee(@RequestBody EmployeeSalaryItem postEmployeeSalaryItem) {
        return new Result<>(salaryItemService.updateToEmployee(postEmployeeSalaryItem));
    }

    @RequestMapping(value = "/employee-salary-item", method = RequestMethod.DELETE)
    public Result<EmployeeSalaryItem> deleteToEmployee(@RequestBody EmployeeSalaryItem postEmployeeSalaryItem) {
        return new Result<>(salaryItemService.deleteToEmployee(postEmployeeSalaryItem));
    }

    @RequestMapping(value = "/employee-salary-item/reset", method = RequestMethod.POST)
    public Result<EmployeeSalaryItem> resetToEmployee(@RequestBody EmployeeQuery employeeQuery) {
        return new Result<>(salaryItemService.resetToEmployee(employeeQuery));

    }
}

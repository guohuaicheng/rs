package com.qiqi.rs.admin.platform.salary.controller;

import com.qiqi.core.model.Result;
import com.qiqi.rs.admin.platform.salary.model.SalaryQuery;
import com.qiqi.rs.admin.platform.salary.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @RequestMapping(value = "/salary", method = RequestMethod.POST)
    public Result<Integer> generate(@RequestBody SalaryQuery postSalaryQuery) {
        return new Result<>(this.salaryService.create(postSalaryQuery));
    }
}

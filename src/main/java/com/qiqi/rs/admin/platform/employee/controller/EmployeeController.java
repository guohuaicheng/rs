package com.qiqi.rs.admin.platform.employee.controller;

import com.qiqi.core.model.Result;
import com.qiqi.core.utils.PageCondition;
import com.qiqi.rs.admin.core.constants.IConstants;
import com.qiqi.rs.admin.platform.employee.model.EmployeeDTO;
import com.qiqi.rs.admin.platform.employee.model.EmployeeQuery;
import com.qiqi.rs.admin.platform.employee.service.EmployeeOptService;
import com.qiqi.rs.admin.platform.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeOptService employeeOptService;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.POST)
    public Result<EmployeeDTO> create(@RequestBody EmployeeDTO postEmployee) {
        return new Result<>(this.employeeOptService.create(postEmployee));
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/join")
    public Result<EmployeeDTO> join(@RequestBody EmployeeDTO postEmployee) {
        return new Result<>(this.employeeOptService.join(postEmployee));
    }


    @RequestMapping(method = RequestMethod.PATCH, value = "/leave")
    public Result<EmployeeDTO> leave(@RequestBody EmployeeDTO postEmployee) {
        return new Result<>(this.employeeOptService.leave(postEmployee));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Result<EmployeeDTO> update(@RequestBody EmployeeDTO postEmployee) {
        return new Result<>(this.employeeOptService.update(postEmployee));
    }

//    @RequestMapping(method = RequestMethod.POST)
//    public Result<Integer> deleteEmployee(@RequestBody Employee postEmployee) {
//        return new Result<>(this.employeeOptService.create(postEmployee));
//    }

    @RequestMapping(method = RequestMethod.POST, value = "/search")
    public Result<List<EmployeeDTO>> queryEmployee(@RequestBody EmployeeQuery employeeQuery,
                                                   @RequestHeader(defaultValue = "0", name = IConstants.RESPONSE_COUNT) boolean doCount,
                                                   @RequestParam(defaultValue = "1") int pageIndex,
                                                   @RequestParam(defaultValue = "10") int pageSize,
                                                   @RequestParam(defaultValue = "asc") String sortType) {
//        EmployeeQuery employeeQuery = new EmployeeQuery();
//        employeeQuery.setOthersQueryMap(searchMap);
        return this.employeeService.query(employeeQuery, new PageCondition(pageIndex, pageSize, doCount));
    }


    @RequestMapping(method = RequestMethod.POST, value = "/salary-item")
    public Result<List<EmployeeDTO>> queryEmployeeWithSalaryItem(@RequestBody EmployeeQuery employeeQuery,
                                                   @RequestHeader(defaultValue = "0", name = IConstants.RESPONSE_COUNT) boolean doCount,
                                                   @RequestParam(defaultValue = "1") int pageIndex,
                                                   @RequestParam(defaultValue = "10") int pageSize,
                                                   @RequestParam(defaultValue = "asc") String sortType) {
//        EmployeeQuery employeeQuery = new EmployeeQuery();
//        employeeQuery.setOthersQueryMap(searchMap);
        return this.employeeService.queryWithSalaryItem(employeeQuery, new PageCondition(pageIndex, pageSize, doCount));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/salary")
    public Result<List<EmployeeDTO>> queryEmployeeWithSalary(@RequestBody EmployeeQuery employeeQuery,
                                                                 @RequestHeader(defaultValue = "0", name = IConstants.RESPONSE_COUNT) boolean doCount,
                                                                 @RequestParam(defaultValue = "1") int pageIndex,
                                                                 @RequestParam(defaultValue = "10") int pageSize,
                                                                 @RequestParam(defaultValue = "asc") String sortType) {
//        EmployeeQuery employeeQuery = new EmployeeQuery();
//        employeeQuery.setOthersQueryMap(searchMap);
        return this.employeeService.queryWithSalary(employeeQuery, new PageCondition(pageIndex, pageSize, doCount));
    }

}

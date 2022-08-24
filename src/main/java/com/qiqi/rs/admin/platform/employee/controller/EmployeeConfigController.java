package com.qiqi.rs.admin.platform.employee.controller;

import com.qiqi.core.model.Result;
import com.qiqi.rs.admin.platform.employee.model.EmployeeConfig;
import com.qiqi.rs.admin.platform.employee.model.EmployeeConfigDTO;
import com.qiqi.rs.admin.platform.employee.model.EmployeeConfigQuery;
import com.qiqi.rs.admin.platform.employee.service.EmployeeConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee-config")
public class EmployeeConfigController {

    @Autowired
    private EmployeeConfigService employeeConfigService;

    @RequestMapping(method = RequestMethod.POST)
    public Result<EmployeeConfigDTO> createEmployeeConfig(@RequestBody EmployeeConfig postEmployeeConfig) {
        return new Result<>(this.employeeConfigService.create(postEmployeeConfig));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Result<EmployeeConfigDTO> modifyEmployeeConfig(@RequestBody EmployeeConfig postEmployeeConfig) {
        return new Result<>(this.employeeConfigService.modify(postEmployeeConfig));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Result<EmployeeConfigDTO> deleteEmployeeConfig(@RequestBody EmployeeConfig postEmployeeConfig) {
        return new Result<>(this.employeeConfigService.delete(postEmployeeConfig));
    }

    @RequestMapping(method = RequestMethod.GET)
    public Result<List<EmployeeConfigDTO>> queryEmployeeConfig(EmployeeConfigQuery employeeConfigQuery) {
        List<EmployeeConfigDTO> list = this.employeeConfigService.query(employeeConfigQuery);
        return new Result<>(list);
    }
}

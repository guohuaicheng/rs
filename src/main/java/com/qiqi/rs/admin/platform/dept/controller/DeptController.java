package com.qiqi.rs.admin.platform.dept.controller;

import com.qiqi.core.model.Result;
import com.qiqi.rs.admin.platform.dept.model.DeptDTO;
import com.qiqi.rs.admin.platform.dept.model.DeptQuery;
import com.qiqi.rs.admin.platform.dept.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService departmentService;

    @RequestMapping(method = RequestMethod.POST)
    public Result<DeptDTO> create(@RequestBody DeptDTO postDepartment) {
        return new Result<>(this.departmentService.create(postDepartment));
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public Result<DeptDTO> update(@RequestBody DeptDTO postDepartment) {
        return new Result<>(this.departmentService.update(postDepartment));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Result<DeptDTO> delete(@RequestBody DeptDTO postDepartment) {
        return new Result<>(this.departmentService.delete(postDepartment));
    }

    @RequestMapping(method = RequestMethod.GET)
    public Result<List<DeptDTO>> query(DeptQuery departmentQuery) {
        return new Result<>(this.departmentService.query(departmentQuery));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tree")
    public Result<List<DeptDTO>> queryTree() {
        return new Result<>(this.departmentService.queryTree());
    }
}

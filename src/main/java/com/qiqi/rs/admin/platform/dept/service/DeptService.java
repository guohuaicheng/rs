package com.qiqi.rs.admin.platform.dept.service;

import com.qiqi.rs.admin.platform.dept.model.DeptDTO;
import com.qiqi.rs.admin.platform.dept.model.DeptQuery;

import java.util.List;

public interface DeptService {

    DeptDTO create(DeptDTO postDepartment);

    DeptDTO update(DeptDTO postDepartment);

    DeptDTO delete(DeptDTO postDepartment);

    List<DeptDTO> query(DeptQuery departmentQuery);

    List<DeptDTO> queryTree();
}

package com.qiqi.rs.admin.platform.employee.service.impl;

import com.github.pagehelper.Page;
import com.qiqi.core.byenum.SortOrderEnum;
import com.qiqi.core.exception.ServiceRuntimeException;
import com.qiqi.core.model.Result;
import com.qiqi.core.utils.PageCondition;
import com.qiqi.core.utils.QiqiPageHelper;
import com.qiqi.rs.admin.core.constants.IConstants;
import com.qiqi.rs.admin.platform.employee.dao.EmployeeDao;
import com.qiqi.rs.admin.platform.employee.model.EmployeeDTO;
import com.qiqi.rs.admin.platform.employee.model.EmployeeQuery;
import com.qiqi.rs.admin.platform.employee.service.EmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public Result<List<EmployeeDTO>> query(EmployeeQuery employeeQuery, PageCondition pageCondition) {
        pageCondition.setSortField("id");
        pageCondition.setSortOrderEnum(SortOrderEnum.ASC);
        QiqiPageHelper.startPage(pageCondition);
        List<EmployeeDTO> list = this.employeeDao.select(employeeQuery);
        Page page = (Page) list;
        return new Result<>(page.getTotal(), list);
    }

    @Override
    public Result<List<EmployeeDTO>> queryWithSalaryItem(EmployeeQuery employeeQuery, PageCondition pageCondition) {
        pageCondition.setSortField("id");
        pageCondition.setSortOrderEnum(SortOrderEnum.ASC);
        QiqiPageHelper.startPage(pageCondition);
        List<EmployeeDTO> list = this.employeeDao.selectWithSalaryItem(employeeQuery);
        Page page = (Page) list;
        return new Result<>(page.getTotal(), list);
    }

    @Override
    public Result<List<EmployeeDTO>> queryWithSalary(EmployeeQuery employeeQuery, PageCondition pageCondition) {
        if (StringUtils.isBlank(employeeQuery.getMonth())) {
            throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_DATA_MSG);
        }
        pageCondition.setSortField("id");
        pageCondition.setSortOrderEnum(SortOrderEnum.ASC);
        QiqiPageHelper.startPage(pageCondition);
        List<EmployeeDTO> list = this.employeeDao.selectWithSalary(employeeQuery);
        Page page = (Page) list;
        return new Result<>(page.getTotal(), list);
    }
}

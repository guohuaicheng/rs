package com.qiqi.rs.admin.platform.employee.service.impl;

import com.qiqi.core.exception.ServiceRuntimeException;
import com.qiqi.rs.admin.core.constants.IConstants;
import com.qiqi.rs.admin.core.utils.SnowFlakedIdWorkerUtil;
import com.qiqi.rs.admin.platform.dept.dao.DeptDao;
import com.qiqi.rs.admin.platform.employee.dao.EmployeeDao;
import com.qiqi.rs.admin.platform.employee.model.Employee;
import com.qiqi.rs.admin.platform.employee.model.EmployeeDTO;
import com.qiqi.rs.admin.platform.employee.model.EmployeeStateEnum;
import com.qiqi.rs.admin.platform.employee.service.EmployeeOptService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeOptServiceImpl implements EmployeeOptService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DeptDao deptDao;


    @Override
    public EmployeeDTO create(EmployeeDTO postEmployee) {

        this.check(postEmployee, 1);
        postEmployee.setId(SnowFlakedIdWorkerUtil.generateId());

        if (deptDao.selectById(postEmployee.getDeptId()) == null) {
            throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_NO_DEPT_MSG);
        }

        postEmployee.removeUnnecessaryFields();

        this.employeeDao.insert(postEmployee);
        return postEmployee;
    }

    @Override
    public EmployeeDTO join(EmployeeDTO postEmployee) {
        if (postEmployee.getId() == null || postEmployee.getJoinDate() == null) {
            throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_DATA_MSG);
        }

        EmployeeDTO employeeDTOInDb = this.employeeDao.selectByIdForOpt(postEmployee.getId());
        if (employeeDTOInDb == null) {
            throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_NO_DATA_MSG);
        }

        if (employeeDTOInDb.getJoinDate() != null) {
            throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, "员工已入职");
        }

        postEmployee.setState(EmployeeStateEnum.JOIN.state());
        this.employeeDao.updateJoin(postEmployee);

        return postEmployee;
    }

    @Override
    public EmployeeDTO leave(EmployeeDTO postEmployee) {
        if (postEmployee.getId() == null || postEmployee.getLeaveDate() == null) {
            throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_DATA_MSG);
        }

        EmployeeDTO employeeDTOInDb = this.employeeDao.selectByIdForOpt(postEmployee.getId());
        if (employeeDTOInDb == null) {
            throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_NO_DATA_MSG);
        }

        if (employeeDTOInDb.getLeaveDate() != null) {
            throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, "员工已离职");
        }

        postEmployee.setState(EmployeeStateEnum.LEAVE.state());
        this.employeeDao.updateLeave(postEmployee);

        return postEmployee;
    }

    @Transactional
    @Override
    public EmployeeDTO update(EmployeeDTO postEmployee) {
        this.check(postEmployee, 2);

        EmployeeDTO employeeDTOInDb = this.employeeDao.selectByIdForOpt(postEmployee.getId());
        if (employeeDTOInDb == null) {
            throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_NO_DATA_MSG);
        }

        if (deptDao.selectById(postEmployee.getDeptId()) == null) {
            throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_NO_DEPT_MSG);
        }

        postEmployee.setState(employeeDTOInDb.getState());
        postEmployee.setEmployeeCode(employeeDTOInDb.getEmployeeCode());

        int d = this.employeeDao.update(postEmployee);

        if (d > 0) {
            return postEmployee;
        } else {
            return null;
        }
    }

    private void check(Employee postEmployee, int action) {
        if (StringUtils.isBlank(postEmployee.getEmployeeCode())
                || StringUtils.isBlank(postEmployee.getEmployeeName())
                || postEmployee.getDeptId() == null) {
            throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_DATA_MSG);
        }

        if (action == 2) {
            if (postEmployee.getId() == null) {
                throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_DATA_MSG);
            }
        }
    }

}

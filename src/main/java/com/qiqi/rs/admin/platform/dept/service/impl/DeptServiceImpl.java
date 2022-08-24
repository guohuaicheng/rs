package com.qiqi.rs.admin.platform.dept.service.impl;

import com.qiqi.core.exception.ServiceRuntimeException;
import com.qiqi.core.utils.QiqiPageHelper;
import com.qiqi.rs.admin.core.constants.IConstants;
import com.qiqi.rs.admin.platform.dept.dao.DeptDao;
import com.qiqi.rs.admin.platform.dept.model.Dept;
import com.qiqi.rs.admin.platform.dept.model.DeptDTO;
import com.qiqi.rs.admin.platform.dept.model.DeptModeEnum;
import com.qiqi.rs.admin.platform.dept.model.DeptQuery;
import com.qiqi.rs.admin.platform.dept.service.DeptService;
import com.qiqi.rs.admin.platform.employee.dao.EmployeeDao;
import com.qiqi.rs.admin.platform.employee.model.EmployeeDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    private final static String CACHE_KEY_PREFIX = "Cache::Depts";
    private final static String CACHE_KEY_TREE = "'Tree'";

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Transactional
    @Override
    @CacheEvict(value = CACHE_KEY_PREFIX, key = CACHE_KEY_TREE)
    public DeptDTO create(DeptDTO postDepartment) {
        this.check(postDepartment, 1);
        DeptDTO parent = null;
        if (postDepartment.getParentId() != null) {
            parent = this.deptDao.selectById(postDepartment.getParentId());
            if (parent == null || parent.getDelFlag()) {
                throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_NO_PARENT_DEPT_MSG);
            } else {
                if (parent.getMode().intValue() != DeptModeEnum.BM.mode()) {
                    throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_DATA_MSG);
                }
            }
        }

        Long id = this.deptDao.selectMaxId();
        if (id == null) id = 1l;
        else id = id.longValue() + 1;

        postDepartment.setId(id);
        if (parent == null) {
            postDepartment.setParentId(-1l);
            postDepartment.setDeptPath("." + id);
        } else {
            postDepartment.setDeptPath(parent.getDeptPath() + "." + id);
        }

        this.deptDao.insert(postDepartment);

        if (parent != null && !parent.getParentFlag()) {
            parent.setParentFlag(true);
            this.deptDao.updateParentFlag(parent);
        }
//        if
        return postDepartment;
    }

    @Override
    @CacheEvict(value = CACHE_KEY_PREFIX, key = CACHE_KEY_TREE)
    public DeptDTO update(DeptDTO postDepartment) {
        this.check(postDepartment, 2);

        DeptDTO departmentDTO = this.deptDao.selectById(postDepartment.getId());
        if (departmentDTO == null) {
            throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_NO_DEPT_MSG);
        }

        this.deptDao.update(postDepartment);

        return postDepartment;
    }

    @Override
    @CacheEvict(value = CACHE_KEY_PREFIX, key = CACHE_KEY_TREE)
    public DeptDTO delete(DeptDTO postDepartment) {
        if (postDepartment.getId() == null) {
            throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_DATA_MSG);
        }

        QiqiPageHelper.startPage(1, 1);
        List<EmployeeDTO> list = this.employeeDao.selectByDeptId(postDepartment.getId());
        if (!CollectionUtils.isEmpty(list)) {
            throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_DEPT_EXIST_EMP_MSG);
        }

        this.deptDao.delete(postDepartment.getId());
        return postDepartment;
    }

    private void check(Dept postDepartment, int action) {
        if (StringUtils.isBlank(postDepartment.getDeptName())) {
            throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_DATA_MSG);
        }
        if (action == 1) {
            if (postDepartment.getMode() == null || DeptModeEnum.valueFrom(postDepartment.getMode()) == null) {
                throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_DATA_MSG);
            }
        } else if (action == 2) {
            if (postDepartment.getId() == null) {
                throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_DATA_MSG);
            }
        }
    }

    @Override
    public List<DeptDTO> query(DeptQuery departmentQuery) {
        return deptDao.select();
    }

    @Override
    @CacheEvict(value = CACHE_KEY_PREFIX, key = CACHE_KEY_TREE)
    public List<DeptDTO> queryTree() {
        return deptDao.selectWithChildren();
    }
}

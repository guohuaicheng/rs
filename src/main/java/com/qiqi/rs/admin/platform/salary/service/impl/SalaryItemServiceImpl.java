package com.qiqi.rs.admin.platform.salary.service.impl;

import com.qiqi.core.exception.ServiceRuntimeException;
import com.qiqi.core.utils.QiqiPageHelper;
import com.qiqi.core.utils.RedisUtil;
import com.qiqi.rs.admin.core.constants.IConstants;
import com.qiqi.rs.admin.core.utils.MathCalculateUtil;
import com.qiqi.rs.admin.core.utils.SnowFlakedIdWorkerUtil;
import com.qiqi.rs.admin.platform.employee.dao.EmployeeDao;
import com.qiqi.rs.admin.platform.employee.model.EmployeeDTO;
import com.qiqi.rs.admin.platform.employee.model.EmployeeQuery;
import com.qiqi.rs.admin.platform.salary.dao.SalaryItemDao;
import com.qiqi.rs.admin.platform.salary.model.EmployeeSalaryItem;
import com.qiqi.rs.admin.platform.salary.model.SalaryItem;
import com.qiqi.rs.admin.platform.salary.model.SalaryItemDTO;
import com.qiqi.rs.admin.platform.salary.model.SalaryItemRuleEnum;
import com.qiqi.rs.admin.platform.salary.model.SalaryItemTypeEnum;
import com.qiqi.rs.admin.platform.salary.service.SalaryItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SalaryItemServiceImpl implements SalaryItemService {

    private final static String CACHE_KEY_PREFIX = "Cache::Salary-Item";
    private final static String CACHE_KEY_ALL = "'All'";

    @Autowired
    private SalaryItemDao salaryItemDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    @CacheEvict(value = CACHE_KEY_PREFIX, key = CACHE_KEY_ALL)
    public SalaryItem create(SalaryItem postSalaryItem) {
        if (StringUtils.isBlank(postSalaryItem.getName()) || postSalaryItem.getType() == null || postSalaryItem.getRule() == null) {
            throw new ServiceRuntimeException(IConstants.DEFAULT_CODE, IConstants.ERROR_DATA_MSG);
        }
        SalaryItemRuleEnum rule = SalaryItemRuleEnum.fromCode(postSalaryItem.getRule());
        SalaryItemTypeEnum type = SalaryItemTypeEnum.fromCode(postSalaryItem.getType());
        if (rule == null || type == null) {
            throw new ServiceRuntimeException(IConstants.DEFAULT_CODE, IConstants.ERROR_DATA_MSG);
        }

        if (rule == SalaryItemRuleEnum.DIRECT) {
            postSalaryItem.setDefaultPrice(null);
        } else if (rule == SalaryItemRuleEnum.PACKAGE) {
            postSalaryItem.setDefaultMoney(null);
        }

        postSalaryItem.setId(SnowFlakedIdWorkerUtil.generateId());
        this.salaryItemDao.insert(postSalaryItem);
        return postSalaryItem;
    }

    @Override
    @CacheEvict(value = CACHE_KEY_PREFIX, key = CACHE_KEY_ALL)
    public SalaryItem update(SalaryItem postSalaryItem) {
        if (StringUtils.isBlank(postSalaryItem.getName()) || postSalaryItem.getId() == null) {
            throw new ServiceRuntimeException(IConstants.DEFAULT_CODE, IConstants.ERROR_DATA_MSG);
        }

        SalaryItem salaryItemInDb = this.salaryItemDao.selectById(postSalaryItem.getId());
        if (salaryItemInDb == null) {
            throw new ServiceRuntimeException(IConstants.DEFAULT_CODE, IConstants.ERROR_DATA_MSG);
        }

        salaryItemInDb.setName(postSalaryItem.getName());

        SalaryItemRuleEnum rule = SalaryItemRuleEnum.fromCode(salaryItemInDb.getRule());
        if (rule == SalaryItemRuleEnum.DIRECT) {
            salaryItemInDb.setDefaultMoney(postSalaryItem.getDefaultMoney());
        } else if (rule == SalaryItemRuleEnum.PACKAGE) {
            salaryItemInDb.setDefaultPrice(postSalaryItem.getDefaultPrice());
        }

        this.salaryItemDao.update(salaryItemInDb);
        return salaryItemInDb;
    }

    @Override
    @CacheEvict(value = CACHE_KEY_PREFIX, key = CACHE_KEY_ALL)
    public SalaryItem delete(SalaryItem postSalaryItem) {
        QiqiPageHelper.startPage(1, 1);

        List<SalaryItem> salaryItemList = this.salaryItemDao.selectBySalaryItemId(postSalaryItem.getId());
        if (CollectionUtils.isEmpty(salaryItemList)) {
            this.salaryItemDao.delete(postSalaryItem);
            return postSalaryItem;
        } else {
            throw new ServiceRuntimeException(IConstants.DEFAULT_CODE, IConstants.ERROR_USED_MSG);
        }
    }

    @Override
    @Cacheable(value = CACHE_KEY_PREFIX, key = CACHE_KEY_ALL)
    public List<SalaryItem> query() {
        return this.salaryItemDao.select();
    }

    @Override
    public EmployeeSalaryItem bindToEmployee(EmployeeSalaryItem postEmployeeSalaryItem) {
        if (postEmployeeSalaryItem.getEmployeeId() == null || postEmployeeSalaryItem.getSalaryItemId() == null) {
            throw new ServiceRuntimeException(IConstants.DEFAULT_CODE, IConstants.ERROR_DATA_MSG);
        }

        EmployeeDTO employeeInDb = this.employeeDao.selectByIdForOpt(postEmployeeSalaryItem.getEmployeeId());
        if (employeeInDb == null) {
            throw new ServiceRuntimeException(IConstants.DEFAULT_CODE, IConstants.ERROR_DATA_MSG);
        }

        SalaryItem salaryItemInDb = this.salaryItemDao.selectById(postEmployeeSalaryItem.getSalaryItemId());
        if (salaryItemInDb == null) {
            throw new ServiceRuntimeException(IConstants.DEFAULT_CODE, IConstants.ERROR_DATA_MSG);
        }

        if (salaryItemInDb.getRule().intValue() == SalaryItemRuleEnum.DIRECT.code()) {
            postEmployeeSalaryItem.setCount(null);
            postEmployeeSalaryItem.setPrice(null);
        } else if (salaryItemInDb.getRule().intValue() == SalaryItemRuleEnum.PACKAGE.code()) {
            if (postEmployeeSalaryItem.getCount() != null && postEmployeeSalaryItem.getPrice() != null) {
                postEmployeeSalaryItem.setMoney(MathCalculateUtil.mul(postEmployeeSalaryItem.getCount(), postEmployeeSalaryItem.getPrice()));
            }
        }

        this.salaryItemDao.insertEmployeeSalaryItem(postEmployeeSalaryItem);
        return postEmployeeSalaryItem;
    }

    @Override
    public EmployeeSalaryItem updateToEmployee(EmployeeSalaryItem postEmployeeSalaryItem) {
        if (postEmployeeSalaryItem.getEmployeeId() == null || postEmployeeSalaryItem.getSalaryItemId() == null) {
            throw new ServiceRuntimeException(IConstants.DEFAULT_CODE, IConstants.ERROR_DATA_MSG);
        }


        SalaryItemDTO salaryItemInDb = this.salaryItemDao.selectByEmployeeIdAndSalaryItemId(postEmployeeSalaryItem.getEmployeeId(), postEmployeeSalaryItem.getSalaryItemId());

        if (salaryItemInDb == null) {
            throw new ServiceRuntimeException(IConstants.DEFAULT_CODE, IConstants.ERROR_DATA_MSG);
        }

        if (salaryItemInDb.getRule().intValue() == SalaryItemRuleEnum.DIRECT.code()) {
            postEmployeeSalaryItem.setCount(null);
            postEmployeeSalaryItem.setPrice(null);
        } else if (salaryItemInDb.getRule().intValue() == SalaryItemRuleEnum.PACKAGE.code()) {
            if (postEmployeeSalaryItem.getCount() != null && postEmployeeSalaryItem.getPrice() != null) {
                postEmployeeSalaryItem.setMoney(MathCalculateUtil.mul(postEmployeeSalaryItem.getCount(), postEmployeeSalaryItem.getPrice()));
            }
        }

        this.salaryItemDao.updateEmployeeSalaryItem(postEmployeeSalaryItem);
        return postEmployeeSalaryItem;
    }

    @Override
    public EmployeeSalaryItem deleteToEmployee(EmployeeSalaryItem postEmployeeSalaryItem) {
        if (postEmployeeSalaryItem.getEmployeeId() == null || postEmployeeSalaryItem.getSalaryItemId() == null) {
            throw new ServiceRuntimeException(IConstants.DEFAULT_CODE, IConstants.ERROR_DATA_MSG);
        }

        this.salaryItemDao.deleteEmployeeSalaryItem(postEmployeeSalaryItem);
        return postEmployeeSalaryItem;
    }

    @Override
    public EmployeeSalaryItem resetToEmployee(EmployeeQuery employeeQuery) {
        this.salaryItemDao.resetEmployeeSalaryItem(employeeQuery);
        return null;
    }
}

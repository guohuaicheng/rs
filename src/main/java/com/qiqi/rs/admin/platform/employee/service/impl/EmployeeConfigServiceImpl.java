package com.qiqi.rs.admin.platform.employee.service.impl;

import com.qiqi.core.exception.ServiceRuntimeException;
import com.qiqi.rs.admin.core.utils.FieldCheckUtil;
import com.qiqi.rs.admin.platform.employee.dao.EmployeeConfigDao;
import com.qiqi.rs.admin.platform.employee.model.EmployeeConfig;
import com.qiqi.rs.admin.platform.employee.model.EmployeeConfigDTO;
import com.qiqi.rs.admin.platform.employee.model.EmployeeConfigQuery;
import com.qiqi.rs.admin.platform.employee.service.EmployeeConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeConfigServiceImpl implements EmployeeConfigService {

    @Autowired
    private EmployeeConfigDao employeeConfigDao;

    @Override
    public EmployeeConfigDTO create(EmployeeConfig postEmployeeConfig) {
        this.check(postEmployeeConfig, 1);
        Long maxId = this.employeeConfigDao.selectMaxId();
        maxId = maxId == null ? 1 : (maxId.longValue() + 1);
        postEmployeeConfig.setId(maxId);
        postEmployeeConfig.setField("a" + maxId);
        this.employeeConfigDao.insert(postEmployeeConfig);

        EmployeeConfigDTO res = new EmployeeConfigDTO();
        res.setId(maxId);
        res.setField(postEmployeeConfig.getField());



        return res;
    }

    @Override
    public EmployeeConfigDTO modify(EmployeeConfig postEmployeeConfig) {
        this.check(postEmployeeConfig, 2);
        this.employeeConfigDao.update(postEmployeeConfig);
        return null;
    }

    @Override
    public EmployeeConfigDTO delete(EmployeeConfig postEmployeeConfig) {
        this.check(postEmployeeConfig, 3);
        this.employeeConfigDao.delete(postEmployeeConfig);
        return null;
    }

    private void check(EmployeeConfig postEmployeeConfig, int action) {
        if (action == 3) {
            if (!FieldCheckUtil.isIdValid(postEmployeeConfig.getId())) {
                throw new ServiceRuntimeException("0", "数据有误");
            }
        }

        if (StringUtils.isBlank(postEmployeeConfig.getFieldName())) {
            throw new ServiceRuntimeException("0", "数据有误");
        }

        if (action == 1) {
            if (postEmployeeConfig.getFieldType() == null) {
                throw new ServiceRuntimeException("0", "数据有误");
            }
        } else if (action == 2) {
            if (!FieldCheckUtil.isIdValid(postEmployeeConfig.getId())) {
                throw new ServiceRuntimeException("0", "数据有误");
            }
        }
    }

    @Override
    public List<EmployeeConfigDTO> query(EmployeeConfigQuery employeeConfigQuery) {
        return employeeConfigDao.select(employeeConfigQuery);
    }
}

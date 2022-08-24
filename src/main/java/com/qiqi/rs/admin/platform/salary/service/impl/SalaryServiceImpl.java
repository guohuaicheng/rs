package com.qiqi.rs.admin.platform.salary.service.impl;

import com.qiqi.core.exception.ServiceRuntimeException;
import com.qiqi.rs.admin.core.constants.IConstants;
import com.qiqi.rs.admin.core.utils.FieldCheckUtil;
import com.qiqi.rs.admin.core.utils.MathCalculateUtil;
import com.qiqi.rs.admin.core.utils.SnowFlakedIdWorkerUtil;
import com.qiqi.rs.admin.platform.employee.dao.EmployeeDao;
import com.qiqi.rs.admin.platform.employee.model.EmployeeDTO;
import com.qiqi.rs.admin.platform.employee.model.EmployeeQuery;
import com.qiqi.rs.admin.platform.salary.dao.SalaryDao;
import com.qiqi.rs.admin.platform.salary.dao.SalaryItemDao;
import com.qiqi.rs.admin.platform.salary.model.EmployeeSalaryItem;
import com.qiqi.rs.admin.platform.salary.model.Salary;
import com.qiqi.rs.admin.platform.salary.model.SalaryItemDTO;
import com.qiqi.rs.admin.platform.salary.model.SalaryItemRuleEnum;
import com.qiqi.rs.admin.platform.salary.model.SalaryItemTypeEnum;
import com.qiqi.rs.admin.platform.salary.model.SalaryQuery;
import com.qiqi.rs.admin.platform.salary.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryDao salaryDao;

    @Autowired
    private SalaryItemDao salaryItemDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public int create(SalaryQuery postSalaryQuery) {
        if (postSalaryQuery.getMonth() == null) {
            throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_DATA_MSG);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        try {
            Date date = sdf.parse(postSalaryQuery.getMonth());
        } catch (ParseException e) {
            throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_DATA_MSG);
        }
        List<EmployeeDTO> employeeDTOList = null;
        SalaryQuery deleteQuery;

        if (postSalaryQuery.getEmployeeId() != null) {
            employeeDTOList = this.employeeDao.selectWithSalaryItem(new EmployeeQuery() {{
                setId(postSalaryQuery.getEmployeeId());
            }});
            deleteQuery = new SalaryQuery() {{
                setEmployeeId(postSalaryQuery.getEmployeeId());
                setMonth(postSalaryQuery.getMonth());
            }};
        } else if (postSalaryQuery.getDeptId() != null) {
            employeeDTOList = this.employeeDao.selectWithSalaryItem(new EmployeeQuery() {{
                setDeptId(postSalaryQuery.getDeptId());
            }});
            deleteQuery = new SalaryQuery() {{
                setDeptId(postSalaryQuery.getDeptId());
                setMonth(postSalaryQuery.getMonth());
            }};
        } else {
            employeeDTOList = this.employeeDao.selectWithSalaryItem(new EmployeeQuery() {{
            }});
            deleteQuery = new SalaryQuery() {{
                setMonth(postSalaryQuery.getMonth());
            }};
        }

        if (CollectionUtils.isEmpty(employeeDTOList)) {
            return 0;
        }

        this.salaryDao.delete(deleteQuery);
        this.salaryDao.deleteEmployeeSalaryItem(deleteQuery);


        List<Salary> createSalaryList = new ArrayList<>(employeeDTOList.size());
        List<EmployeeSalaryItem> employeeSalaryItemList = new ArrayList<>();
        for (EmployeeDTO employeeDTO : employeeDTOList) {
            long salaryId = SnowFlakedIdWorkerUtil.generateId();
            double money = 0;
            if (!CollectionUtils.isEmpty(employeeDTO.getSalaryItemList())) {
                for (SalaryItemDTO salaryItem : employeeDTO.getSalaryItemList()) {
                    EmployeeSalaryItem create = new EmployeeSalaryItem();
                    create.setId(SnowFlakedIdWorkerUtil.generateId());
                    create.setSalaryId(salaryId);
                    create.setEmployeeId(employeeDTO.getId());
                    create.setSalaryItemId(salaryItem.getId());
                    create.setMonth(postSalaryQuery.getMonth());

                    if (salaryItem.getRule() == SalaryItemRuleEnum.PACKAGE.code()) {
                        create.setCount(salaryItem.getCount());
                        create.setPrice(salaryItem.getPrice());
                        create.setMoney(MathCalculateUtil.mul(FieldCheckUtil.getValueOrDefault(create.getCount()), FieldCheckUtil.getValueOrDefault(create.getPrice())));
                    } else if (salaryItem.getRule() == SalaryItemRuleEnum.DIRECT.code()) {
                        create.setMoney(FieldCheckUtil.getValueOrDefault(salaryItem.getMoney()));
                    } else {
                        throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_INNER_MSG);
                    }

                    SalaryItemTypeEnum typeEnum = SalaryItemTypeEnum.fromCode(salaryItem.getType());
                    if (typeEnum == SalaryItemTypeEnum.ADD) {
                        money = MathCalculateUtil.add(money, create.getMoney());
                    } else if (typeEnum == SalaryItemTypeEnum.MINUS) {
                        money = MathCalculateUtil.sub(money, create.getMoney());
                    }
                    employeeSalaryItemList.add(create);
                }
            }

            Salary salary = new Salary() {{
                setId(salaryId);
                setDeptId(employeeDTO.getDept().getId());
                setEmployeeId(employeeDTO.getId());
                setMonth(postSalaryQuery.getMonth());
            }};
            salary.setSalary(money);

            createSalaryList.add(salary);
        }

        if (!CollectionUtils.isEmpty(employeeSalaryItemList)) {
            this.salaryDao.insertEmployeeSalaryItemList(employeeSalaryItemList);
        }

        return this.salaryDao.insertList(createSalaryList);
    }

}

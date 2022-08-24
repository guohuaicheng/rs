package com.qiqi.rs.admin.platform.employee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.qiqi.core.utils.JsonUtil;
import com.qiqi.rs.admin.platform.base.PlatformBase;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee extends PlatformBase {
    private final static String fixedColumn = ",id,employeeCode,employeeName,idNo,deptId,dept,joinDate,leaveDate,state," ;

    private String employeeCode;
    private String employeeName;
    private String idNo;
    private Long deptId;
    private Date joinDate;
    private Date leaveDate;
    private Integer state;



    private Map<String, String> others;
    public String othersInfo;

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public Map<String, String> getOthers() {
        if (others == null) {
            others = JsonUtil.jsonToBean(othersInfo, HashMap.class);
        }
        return others;
    }

    public void setOthers(Map<String, String> others) {
        this.others = others;
    }

    @JsonIgnore
    public String getOthersInfo() {
        if (StringUtils.isBlank(othersInfo)) {
            othersInfo = JsonUtil.beanToJson(others);
        }
        return othersInfo;
    }

    public void setOthersInfo(String othersInfo) {
        this.othersInfo = othersInfo;
    }


    public void removeUnnecessaryFields() {
        if (!CollectionUtils.isEmpty(this.getOthers())) {
            Iterator<Map.Entry<String, String>> iter = this.getOthers().entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, String> entry = iter.next();
                if (fixedColumn.indexOf(entry.getKey()) > 0) {
                    iter.remove();
                }
            }

            this.setOthersInfo(null);
        }
    }
}

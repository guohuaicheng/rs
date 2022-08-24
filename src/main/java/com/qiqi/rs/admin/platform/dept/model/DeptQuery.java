package com.qiqi.rs.admin.platform.dept.model;

import com.qiqi.rs.admin.platform.base.QueryBase;

public class DeptQuery extends QueryBase {

    private String deptName;
    private Long parentId;
    private String deptPath;
    private Boolean parentFlag;


    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDeptPath() {
        return deptPath;
    }

    public void setDeptPath(String deptPath) {
        this.deptPath = deptPath;
    }

    public Boolean getParentFlag() {
        return parentFlag;
    }

    public void setParentFlag(Boolean parentFlag) {
        this.parentFlag = parentFlag;
    }
}

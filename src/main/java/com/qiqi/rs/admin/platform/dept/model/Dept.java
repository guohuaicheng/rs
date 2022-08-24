package com.qiqi.rs.admin.platform.dept.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.qiqi.rs.admin.platform.base.PlatformBase;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dept extends PlatformBase {

    private String deptName;
    private Long parentId;
    private String deptPath;
    private Boolean parentFlag;
    private Integer mode;

//    private Integer[] deptPathArr;



//    public void setDeptPathArr(Integer[] deptPathArr) {
//        this.deptPathArr = deptPathArr;
//    }

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

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }
}

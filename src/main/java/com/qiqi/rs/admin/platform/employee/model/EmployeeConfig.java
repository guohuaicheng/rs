package com.qiqi.rs.admin.platform.employee.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.qiqi.rs.admin.platform.base.PlatformBase;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeConfig extends PlatformBase {
    private String field;
    private String fieldName;

    /**
     * 1 数字
     * 2 整数
     * 3 字符串
     * 4 时间
     *
     */
    private Integer fieldType;

    private Integer state;
    private String refId;
    private String candidateVal;
    private String format;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Integer getFieldType() {
        return fieldType;
    }

    public void setFieldType(Integer fieldType) {
        this.fieldType = fieldType;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCandidateVal() {
        return candidateVal;
    }

    public void setCandidateVal(String candidateVal) {
        this.candidateVal = candidateVal;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}

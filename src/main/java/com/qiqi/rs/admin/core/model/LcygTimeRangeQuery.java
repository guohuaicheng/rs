package com.qiqi.rs.admin.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class LcygTimeRangeQuery {
    private Date startTime;
    private Date endTime;

    private Long st; //开始时间戳
    private Long et; //结束时间戳

    private String keyword;

    private Boolean queryStat = false;

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    @JsonIgnore
    public void setStartTimeManually(Date startTime) {
        this.startTime = startTime;
        if (startTime != null) st = startTime.getTime();
    }

    @JsonIgnore
    public void setEndTimeManually(Date endTime) {
        this.endTime = endTime;
        if (endTime != null) et = endTime.getTime();
    }

    public void setSt(Long st) {
        this.st = st;
        if (this.st != null) {
            this.startTime = new Date(st.longValue());
        }
    }

    public void setEt(Long et) {
        this.et = et;
        if (this.et != null) {
            this.endTime = new Date(et.longValue());
        }
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getSt() {
        return this.st;
    }

    public Long getEt() {
        return this.et;
    }

    public Boolean getQueryStat() {
        return queryStat;
    }

    public void setQueryStat(Boolean queryStat) {
        this.queryStat = queryStat;
    }
}

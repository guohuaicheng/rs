package com.qiqi.rs.admin.platform.dict.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * 产地
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AreaVO {

    private String areaName;
    private List<Area> subList;

    public List<Area> getSubList() {
        return subList;
    }

    public void setSubList(List<Area> subList) {
        this.subList = subList;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}

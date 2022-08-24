package com.qiqi.rs.admin.platform.salary.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.qiqi.rs.admin.platform.base.PlatformBase;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SalaryItem extends PlatformBase {

    private String name;
    private Integer type;
    private Integer rule;
    private Double defaultPrice;
    private Double defaultMoney;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getRule() {
        return rule;
    }

    public void setRule(Integer rule) {
        this.rule = rule;
    }

    public Double getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(Double defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public Double getDefaultMoney() {
        return defaultMoney;
    }

    public void setDefaultMoney(Double defaultMoney) {
        this.defaultMoney = defaultMoney;
    }
}

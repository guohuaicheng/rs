package com.qiqi.rs.admin.core.model;

import org.apache.commons.lang3.StringUtils;

public enum ImageModuleEnum {

    PRODUCT("product"), PAYMENT_CODE("payment_code"), WORK_ORDER("work_order");

    private String module;

    ImageModuleEnum(String module) {
        this.module = module;
    }

    public String module() {
        return this.module;
    }

    public static ImageModuleEnum valueFrom(String code) {
        for (ImageModuleEnum imageModuleEnum : ImageModuleEnum.values()) {
            if (StringUtils.equals(code, imageModuleEnum.module)) {
                return imageModuleEnum;
            }
        }
        return null;
    }

}

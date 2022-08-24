package com.qiqi.rs.admin.core.model;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum DecimalFormatInstanceEnum {

    INSTANCE_0000("0000"), INSTANCE_00("00"), INSTANCE_0_00("0.00");

    private Map<String, DecimalFormat> decimalFormatMap = new ConcurrentHashMap<>(2);

    private String pattern;

    DecimalFormatInstanceEnum(String pattern) {
        this.pattern = pattern;
        decimalFormatMap.put(pattern, new DecimalFormat(pattern));
    }

    public DecimalFormat getDecimalFormat() {
        return decimalFormatMap.get(this.pattern);
    }
}

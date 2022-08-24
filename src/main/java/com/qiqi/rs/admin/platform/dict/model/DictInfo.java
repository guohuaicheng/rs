package com.qiqi.rs.admin.platform.dict.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.qiqi.rs.admin.platform.base.PlatformBase;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class DictInfo extends PlatformBase {
    private Long dictId;
    private String dictName;
    private Long dictValue;
    @JsonIgnore
    private int px;

    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    public Long getDictValue() {
        return dictValue;
    }

    public void setDictValue(Long dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }
}

package com.qiqi.rs.admin.platform.dict.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.qiqi.rs.admin.platform.base.PlatformBase;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dict extends PlatformBase {
    private String dictCode;
    private String dictName;

    private List<DictInfo> dictInfoList;

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public List<DictInfo> getDictInfoList() {
        return dictInfoList;
    }

    public void setDictInfoList(List<DictInfo> dictInfoList) {
        this.dictInfoList = dictInfoList;
    }
}

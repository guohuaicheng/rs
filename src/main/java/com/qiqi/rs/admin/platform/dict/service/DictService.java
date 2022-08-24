package com.qiqi.rs.admin.platform.dict.service;

import com.qiqi.core.utils.PageCondition;
import com.qiqi.rs.admin.platform.dict.model.Dict;
import com.qiqi.rs.admin.platform.dict.model.DictInfo;
import com.qiqi.rs.admin.platform.dict.model.DictQuery;

import java.util.List;

public interface DictService {

    Dict create(Dict postDict);

    Dict update(Dict postDict);

    Dict delete(Dict postDict);

    List<Dict> queryWithInfo(DictQuery dictQuery, PageCondition pageCondition);


    DictInfo createInfo(DictInfo postDictInfo);

    DictInfo updateInfo(DictInfo postDictInfo);

    DictInfo deleteInfo(DictInfo postDictInfo);
}

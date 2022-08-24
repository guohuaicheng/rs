package com.qiqi.rs.admin.platform.dict.dao;

import com.qiqi.rs.admin.platform.dict.model.Dict;
import com.qiqi.rs.admin.platform.dict.model.DictInfo;
import com.qiqi.rs.admin.platform.dict.model.DictQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DictDao {

    int insert(Dict dict);

    int update(Dict dict);

    int delete(@Param("id") long id);

    Dict selectById(@Param("id") long id);

    List<Dict> selectWithInfo(DictQuery dictQuery);


    int insertInfo(DictInfo dictInfo);

    int updateInfo(DictInfo dictInfo);

    int deleteInfo(@Param("id") long id);

    DictInfo selectInfoById(@Param("id") long id);

}

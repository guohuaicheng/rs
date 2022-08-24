package com.qiqi.rs.admin.platform.dict.dao;

import com.qiqi.rs.admin.platform.dict.model.Area;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AreaDao {

    int insert(Area area);

//    int insertList(@Param("areaList") List<Area> areaList);

    List<Area> select(Area area);

    List<Area> selectBasicInfo(Area area);

    List<Area> selectBasicInfoWithSub(@Param("parentCode") String parentCode);

    int update(Area pojo);

}

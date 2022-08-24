package com.qiqi.rs.admin.platform.dict.service;

import com.qiqi.core.utils.PageCondition;
import com.qiqi.rs.admin.platform.dict.model.Area;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AreaService {

    int createArea(Area area);

    List<Area> queryAreas(Area area, PageCondition pageCondition);

    List<Area> queryAreasBasicInfo(Area area, PageCondition pageCondition);

    List<Area> queryAreasBasicInfoWithSub(String parentCode);

    int updateArea(Area area);

}

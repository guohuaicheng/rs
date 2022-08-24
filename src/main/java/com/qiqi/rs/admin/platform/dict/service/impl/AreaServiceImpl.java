package com.qiqi.rs.admin.platform.dict.service.impl;

import com.qiqi.core.utils.PageCondition;
import com.qiqi.core.utils.QiqiPageHelper;
import com.qiqi.rs.admin.platform.dict.dao.AreaDao;
import com.qiqi.rs.admin.platform.dict.model.Area;
import com.qiqi.rs.admin.platform.dict.service.AreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Resource
    private AreaDao areaDao;

    @Override
    public int createArea(Area area) {
        return areaDao.insert(area);
    }

    @Override
    public List<Area> queryAreas(Area area, PageCondition pageCondition) {
        if (area == null) {
            area = new Area();
        }
        QiqiPageHelper.startPage(pageCondition);
        return areaDao.select(area);
    }

    @Override
    public List<Area> queryAreasBasicInfo(Area area, PageCondition pageCondition) {
        if (area == null) {
            area = new Area();
        }
        QiqiPageHelper.startPage(pageCondition);
        return areaDao.selectBasicInfo(area);
    }

    @Override
    public List<Area> queryAreasBasicInfoWithSub(String parentCode) {
        return areaDao.selectBasicInfoWithSub(parentCode);
    }

    @Override
    public int updateArea(Area area) {
        return areaDao.update(area);
    }

}

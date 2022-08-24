package com.qiqi.rs.admin.platform.dict.controller;

import com.github.pagehelper.Page;
import com.qiqi.core.byenum.SortOrderEnum;
import com.qiqi.core.model.Result;
import com.qiqi.core.utils.PageCondition;
import com.qiqi.rs.admin.platform.dict.model.Area;
import com.qiqi.rs.admin.platform.dict.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/platform/areas")
public class AreaController {
    @Autowired
    private AreaService areaService;

    /*@RequestMapping(method = RequestMethod.GET)
    public Result<List<Area>> queryTree(
                                    @RequestParam(defaultValue = "1") int pageIndex,
                                    @RequestParam(defaultValue = "100") int pageSize,
                                    @RequestParam(defaultValue = "areaCode") String sortField,
                                    @RequestParam(defaultValue = "asc") String sortType) {
        Area area = new Area();
        PageCondition pageCondition = new PageCondition(pageIndex, pageSize, true, sortField, sortType);
        List<Area> list = this.areaService.queryAreas(area, pageCondition);
        Page page = (Page) list;
        return new Result<List<Area>>(page.getTotal(), list);
    }*/

    @RequestMapping(value = "/{areaCode}", method = RequestMethod.GET)
    public Result<List<Area>> queryByAreaCode(@PathVariable(value = "areaCode", required = true) String areaCode) {
        // TODO 从缓存中获取

        PageCondition pageCondition = new PageCondition(1, 100, true, "areaCode", SortOrderEnum.ASC);
        Area area = new Area();
        area.setParentCode(areaCode);
        List<Area> list = this.areaService.queryAreasBasicInfo(area, pageCondition);
        Page page = (Page) list;
        return new Result<List<Area>>(page.getTotal(), list);
    }

    @RequestMapping(value = "/parent-sub/{areaCode}", method = RequestMethod.GET)
    public Result<List<Area>> queryByAreaCodeWithSub(@PathVariable(value = "areaCode", required = true) String areaCode) {
        return new Result<List<Area>>(this.areaService.queryAreasBasicInfoWithSub(areaCode));
    }


}

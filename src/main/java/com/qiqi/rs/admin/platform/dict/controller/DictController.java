package com.qiqi.rs.admin.platform.dict.controller;

import com.qiqi.core.byenum.SortOrderEnum;
import com.qiqi.core.model.Result;
import com.qiqi.core.utils.PageCondition;
import com.qiqi.rs.admin.core.constants.IConstants;
import com.qiqi.rs.admin.platform.dict.model.Dict;
import com.qiqi.rs.admin.platform.dict.model.DictInfo;
import com.qiqi.rs.admin.platform.dict.model.DictQuery;
import com.qiqi.rs.admin.platform.dict.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/platform/dicts")
public class DictController {

    @Autowired
    private DictService dictService;

    @RequestMapping(method = RequestMethod.POST)
    public Result<Dict> create(@RequestBody Dict dict) {
        return new Result(this.dictService.create(dict));
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public Result<Dict> update(@RequestBody Dict dict) {
        return new Result(this.dictService.update(dict));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Result<Dict> delete(@RequestBody Dict dict) {
        return new Result(this.dictService.delete(dict));
    }


    @RequestMapping(method = RequestMethod.GET, value = "/opt")
    public Result<List<Dict>> query(DictQuery dictQuery,
                                    @RequestHeader(defaultValue = "0", name = IConstants.RESPONSE_COUNT) boolean doCount,
                                    @RequestParam(defaultValue = "1") int pageIndex,
                                    @RequestParam(defaultValue = "10") int pageSize,
                                    @RequestParam(defaultValue = "desc") String sortType) {
        List<Dict> list = this.dictService.queryWithInfo(dictQuery, new PageCondition(pageIndex, pageSize, doCount, "id", SortOrderEnum.valueFrom(sortType)));
        return new Result(list);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/info")
    public Result<DictInfo> createInfo(@RequestBody DictInfo dictInfo) {
        return new Result(this.dictService.createInfo(dictInfo));
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/info")
    public Result<DictInfo> updateInfo(@RequestBody DictInfo dictInfo) {
        return new Result(this.dictService.updateInfo(dictInfo));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/info")
    public Result<DictInfo> deleteInfo(@RequestBody DictInfo dictInfo) {
        return new Result(this.dictService.deleteInfo(dictInfo));
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public Result<List<DictInfo>> query(@RequestParam String dictCode) {
//        Map<String, String> map = this.dictService.queryDictInfos(dictCode);
//        List<DictInfo> list = new ArrayList<DictInfo>();
//        DictInfo dictInfo = null;
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            dictInfo = new DictInfo();
//            dictInfo.setDictValue(entry.getKey());
//            dictInfo.setDictName(entry.getValue());
//            list.add(dictInfo);
//        }
//        return new Result(list);
//    }

//    @RequestMapping(method = RequestMethod.GET)
//    public void init() {
//        this.dictService.initDict();
//    }

//    @RequestMapping(value = "/d/xx", method = RequestMethod.GET)
//    public Result<Integer> create(Dict dict) {
//        this.dictService.create(dict);
//        return new Result<Integer>(1);
//    }
}

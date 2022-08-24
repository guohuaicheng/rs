package com.qiqi.rs.admin.platform.dict.service.impl;

import com.qiqi.core.exception.ServiceRuntimeException;
import com.qiqi.core.utils.PageCondition;
import com.qiqi.core.utils.QiqiPageHelper;
import com.qiqi.rs.admin.core.constants.IConstants;
import com.qiqi.rs.admin.core.utils.SnowFlakedIdWorkerUtil;
import com.qiqi.rs.admin.platform.dict.dao.DictDao;
import com.qiqi.rs.admin.platform.dict.model.Dict;
import com.qiqi.rs.admin.platform.dict.model.DictInfo;
import com.qiqi.rs.admin.platform.dict.model.DictQuery;
import com.qiqi.rs.admin.platform.dict.service.DictService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private DictDao dictDao;

    @Override
    public List<Dict> queryWithInfo(DictQuery dictQuery, PageCondition pageCondition) {
        QiqiPageHelper.startPage(pageCondition);
        return this.dictDao.selectWithInfo(dictQuery);
    }

    @Override
    public Dict create(Dict postDict) {
        this.validate(postDict, 1);
        postDict.setId(SnowFlakedIdWorkerUtil.generateId());
        this.dictDao.insert(postDict);
        return postDict;
    }

    @Override
    public Dict update(Dict postDict) {
        this.validate(postDict, 2);

        Dict dictInDb = this.dictDao.selectById(postDict.getId());
        if (dictInDb == null) {
            throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_NO_DATA_MSG);
        }

        postDict.setDictCode(dictInDb.getDictCode());

        this.dictDao.update(postDict);
        return postDict;
    }

    @Override
    public Dict delete(Dict postDict) {

        if (postDict.getId() == null) {
            throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_DATA_MSG);
        }

        Dict dictInDb = this.dictDao.selectById(postDict.getId());
        if (dictInDb == null) {
            throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_NO_DATA_MSG);
        }

        this.dictDao.delete(postDict.getId());
        return postDict;
    }

    private void validate(Dict postDict, int action) {
        if (StringUtils.isBlank(postDict.getDictName())) {
            throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_DATA_MSG);
        }

        if (action == 1) {
            if (StringUtils.isBlank(postDict.getDictCode())) {
                throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_DATA_MSG);
            }
        } else if (action == 2) {
            if (postDict.getId() == null) {
                throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_DATA_MSG);
            }
        }
    }


    @Override
    public DictInfo createInfo(DictInfo postDictInfo) {

        Dict dictInDb = this.dictDao.selectById(postDictInfo.getDictId());
        if (dictInDb == null) {
            throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_NO_DATA_MSG);
        }

        postDictInfo.setId(SnowFlakedIdWorkerUtil.generateId());
        postDictInfo.setDictValue(SnowFlakedIdWorkerUtil.generateId());
        this.dictDao.insertInfo(postDictInfo);

        return postDictInfo;
    }

    @Override
    public DictInfo updateInfo(DictInfo postDictInfo) {
        this.validateDictInfo(postDictInfo, 2);

        DictInfo dictInfoInDb = this.dictDao.selectInfoById(postDictInfo.getId());
        if (dictInfoInDb == null) {
            throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_NO_DATA_MSG);
        }

        this.dictDao.updateInfo(postDictInfo);
        return postDictInfo;
    }

    @Override
    public DictInfo deleteInfo(DictInfo postDictInfo) {
        if (postDictInfo.getId() == null) {
            throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_DATA_MSG);
        }

        DictInfo dictInfoInDb = this.dictDao.selectInfoById(postDictInfo.getId());
        if (dictInfoInDb == null) {
            throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_NO_DATA_MSG);
        }

        this.dictDao.deleteInfo(postDictInfo.getId());
        return postDictInfo;
    }

    private void validateDictInfo(DictInfo postDictInfo, int action) {
        if (StringUtils.isBlank(postDictInfo.getDictName())) {
            throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_DATA_MSG);
        }

        if (action == 1) {
            if (postDictInfo.getDictId() == null) {
                throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_DATA_MSG);
            }
        } else if (action == 2) {
            if (postDictInfo.getId() == null) {
                throw new ServiceRuntimeException(IConstants.ERROR_DEFAULT_CODE, IConstants.ERROR_DATA_MSG);
            }
        }
    }
}

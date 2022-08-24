package com.qiqi.rs.admin.platform.user.service.impl;

import com.qiqi.core.utils.IDGenerator;
import com.qiqi.core.utils.PageCondition;
import com.qiqi.core.utils.QiqiPageHelper;
import com.qiqi.rs.admin.core.constants.IConstants;
import com.qiqi.rs.admin.core.exception.ExceptionDefinition;
import com.qiqi.rs.admin.core.model.DecimalFormatInstanceEnum;
import com.qiqi.rs.admin.platform.user.dao.UserAuthDao;
import com.qiqi.rs.admin.platform.user.dao.UserInfoDao;
import com.qiqi.rs.admin.platform.user.model.RoleEnum;
import com.qiqi.rs.admin.platform.user.model.UserAuth;
import com.qiqi.rs.admin.platform.user.model.UserInfo;
import com.qiqi.rs.admin.platform.user.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private UserAuthDao userAuthDao;

    @Override
    @Transactional
    public int createUser(UserInfo userInfo) {
        this.validateCreateInfo(userInfo);

        userInfo.setUsername(userInfo.getUserCode());

        UserAuth userAuth = new UserAuth();
        BeanUtils.copyProperties(userInfo, userAuth);
        userAuth.setUserId(IDGenerator.generateID());
        userAuthDao.insert(userAuth);

        userInfo.setUserId(userAuth.getUserId());
        userInfo.initForCreate();
        return userInfoDao.insert(userInfo);
    }

    @Override
    public int updateUser(UserInfo userInfo) {
        UserInfo old = this.validateUpdateInfo(userInfo);

        if ((StringUtils.isNotBlank(userInfo.getMobilePhone()) && !userInfo.getMobilePhone().equals(old.getMobilePhone()))
                || (StringUtils.isNotBlank(userInfo.getPassword()) && !userInfo.getPassword().equals(old.getPassword()))
                || (StringUtils.isNotBlank(userInfo.getUserCode()) && !userInfo.getUserCode().equals(old.getUserCode()))) {

            UserAuth userAuth = userAuthDao.selectByUsername(old.getUsername());
            userAuth.setMobilePhone(userInfo.getMobilePhone());
            userAuth.setPassword(userInfo.getPassword());
            userAuth.setUserCode(userInfo.getUserCode());
            userAuthDao.update(userAuth);
        }

        userInfo.initForUpdate();
        return userInfoDao.update(userInfo);
    }


    @Transactional
    @Override
    public int deleteUser(String userId) {

        if (StringUtils.isBlank(userId)) {
            return 0;
        }

        // 判断是否存在
        UserInfo userInfo = this.userInfoDao.selectById(userId);
        if (userInfo == null) {
            return 0;
        }

        if (StringUtils.equals(userInfo.getRole(), RoleEnum.ADMIN.code())) {
            throw ExceptionDefinition.PERMISSION_ERROR.exception();
        }

        // 执行业务删除
        UserInfo arg = new UserInfo();
        arg.setUserId(userId);
        arg.setDelFlag(true);
        arg.initForUpdate();
        return this.userInfoDao.update(arg);
    }

    private void validateCreateInfo(UserInfo newUser) {
        if (newUser == null
                || StringUtils.isBlank(newUser.getPassword())
                || StringUtils.isBlank(newUser.getUserCode())
                || StringUtils.isBlank(newUser.getMobilePhone())) {
            throw ExceptionDefinition.USER_LACK_INFO_ERROR.exception();
        }
        //校验编码是否存在
        if (userInfoDao.selectByCodeForCheckIfExists(newUser.getUserCode()) != null) {
            throw new RuntimeException("编号[" + newUser.getUserCode() + "]已存在！");
        }

        if (userInfoDao.selectByNameForCheckIfExists(newUser.getNickname()) != null) {
            throw new RuntimeException("名称[" + newUser.getNickname() + "]已存在！");
        }

        if (userInfoDao.selectByMobileForCheckIfExists(newUser.getMobilePhone()) != null) {
            throw new RuntimeException("手机[" + newUser.getMobilePhone() + "]已存在！");
        }
    }

    private UserInfo validateUpdateInfo(UserInfo newUser) {
        if (newUser == null || StringUtils.isBlank(newUser.getUserId())) {
            throw ExceptionDefinition.USER_LACK_INFO_ERROR.exception();
        }

        UserInfo _old = this.userInfoDao.selectByIdForOpt(newUser.getUserId());
        if (_old == null) {
            throw ExceptionDefinition.USER_UPDATE_NULL_ERROR.exception(newUser.getUserId());
        }

        if (StringUtils.isNotBlank(newUser.getNickname()) && !_old.getNickname().equals(newUser.getNickname())) {
            if (userInfoDao.selectByNameForCheckIfExists(newUser.getNickname()) != null) {
                throw new RuntimeException("名称[" + newUser.getNickname() + "]已存在！");
            }
        }

        if (StringUtils.isNotBlank(newUser.getMobilePhone()) && !_old.getMobilePhone().equals(newUser.getMobilePhone())) {
            if (userInfoDao.selectByMobileForCheckIfExists(newUser.getMobilePhone()) != null) {
                throw new RuntimeException("手机[" + newUser.getMobilePhone() + "]已存在！");
            }
        }
        return _old;
    }

    @Override
    public String queryNextCode() {
        String code = this.userInfoDao.selectLastCode();

        if (StringUtils.isBlank(code)) {
            return IConstants.DEFAULT_START_CODE;
        }
        int maxCode = Integer.parseInt(code);
        if (IConstants.DEFAULT_CODE.equals(code)) {
            return IConstants.DEFAULT_START_CODE;
        } else if (maxCode != 9999) {
            return DecimalFormatInstanceEnum.INSTANCE_0000.getDecimalFormat().format(Integer.parseInt(code) + 1);
        } else {
            return DecimalFormatInstanceEnum.INSTANCE_0000.getDecimalFormat().format(maxCode);
        }
    }

    @Override
    public List<UserInfo> queryUsers(UserInfo userInfo, PageCondition pageCondition) {
        if (userInfo == null) {
            userInfo = new UserInfo();
        }

        QiqiPageHelper.startPage(pageCondition);
        return userInfoDao.select(userInfo);
    }

    @Override
    public List<UserInfo> queryUsersBasicInfo(UserInfo userInfo, PageCondition pageCondition) {
        if (userInfo == null) {
            userInfo = new UserInfo();
        }
        QiqiPageHelper.startPage(pageCondition);
        return userInfoDao.selectBasicInfo(userInfo);
    }

    @Override
    public UserInfo queryUserById(String userId) {
        if (StringUtils.isBlank(userId)) {
            return null;
        }
        return this.userInfoDao.selectById(userId);
    }
}

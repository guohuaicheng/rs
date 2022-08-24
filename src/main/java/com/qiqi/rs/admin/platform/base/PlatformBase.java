package com.qiqi.rs.admin.platform.base;

import com.qiqi.core.runtime.threadlocal.ClientInfoThreadLocalHolder;
import com.qiqi.core.utils.DateUtil;
import com.qiqi.rs.admin.core.runtime.threadlocal.UserContextThreadLocalHolder;
import com.qiqi.rs.admin.platform.user.model.CachedUser;

import java.util.Date;

public class PlatformBase extends BaseModel {

    public void initForCreate(Date... createdTime) {

        CachedUser cachedUser = UserContextThreadLocalHolder.get();

        if (cachedUser != null) {
            this.setCreatedUser(cachedUser.getUserId());
            this.setCreatedDevice(cachedUser.getDevice());
        } else {
            this.setCreatedUser("");
            this.setCreatedDevice("");
        }

        this.setCreatedTime(createdTime.length > 0 ? createdTime[0] : DateUtil.getCurrentDate());
        this.setCreatedDeviceIp(ClientInfoThreadLocalHolder.get().getIp());

        this.setUpdatedDevice(null);
        this.setUpdatedDeviceIp(null);
        this.setUpdatedUser(null);
        this.setUpdatedTime(null);
    }

    public void initForUpdate() {

        CachedUser cachedUser = UserContextThreadLocalHolder.get();

        if (cachedUser != null) {
            this.setUpdatedUser(cachedUser.getUserId());
            this.setUpdatedDevice(cachedUser.getDevice());
        } else {
            this.setUpdatedUser("");
            this.setUpdatedDevice("");
        }
        this.setUpdatedTime(DateUtil.getCurrentDate());
        this.setUpdatedDeviceIp(ClientInfoThreadLocalHolder.get().getIp());

        this.setCreatedUser(null);
        this.setCreatedTime(null);
        this.setCreatedDevice(null);
        this.setCreatedDeviceIp(null);
    }

}

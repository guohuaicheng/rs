package com.qiqi.rs.admin.platform.base;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qiqi.core.json.serializer.BooleanDeserializer;
import com.qiqi.core.json.serializer.BooleanSerializer;

import java.util.Date;

public class BaseModel {

    private Long id;

    protected String description; // 描述
    private Boolean enableFlag; // 是否启用
    private Boolean delFlag = false; // 是否删除

    private String createdUser; // 创建人
    private Date createdTime; // 创建时间
    private String updatedUser; // 修改人
    private Date updatedTime; // 修改时间

    private String createdDevice; // 创建数据的设备
    private String createdDeviceIp; // 设备ip
    private String updatedDevice; // 修改数据的设备
    private String updatedDeviceIp; // 设备ip

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
    }

    @JsonSerialize(using = BooleanSerializer.class)
    public Boolean getEnableFlag() {
        return enableFlag;
    }

    @JsonDeserialize(using = BooleanDeserializer.class)
    public void setEnableFlag(Boolean enableFlag) {
        this.enableFlag = enableFlag;
    }

    @JsonSerialize(using = BooleanSerializer.class)
    public Boolean getDelFlag() {
        return delFlag;
    }

    @JsonDeserialize(using = BooleanDeserializer.class)
    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedDevice() {
        return createdDevice;
    }

    public void setCreatedDevice(String createdDevice) {
        this.createdDevice = createdDevice;
    }

    public String getCreatedDeviceIp() {
        return createdDeviceIp;
    }

    public void setCreatedDeviceIp(String createdDeviceIp) {
        this.createdDeviceIp = createdDeviceIp;
    }

    public String getUpdatedDevice() {
        return updatedDevice;
    }

    public void setUpdatedDevice(String updatedDevice) {
        this.updatedDevice = updatedDevice;
    }

    public String getUpdatedDeviceIp() {
        return updatedDeviceIp;
    }

    public void setUpdatedDeviceIp(String updatedDeviceIp) {
        this.updatedDeviceIp = updatedDeviceIp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

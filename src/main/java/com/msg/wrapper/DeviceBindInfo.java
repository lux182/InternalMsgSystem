package com.msg.wrapper;

import com.baidu.yun.channel.model.BindInfo;

/*
 * The wrapper for the class com.baidu.yun.channel.model.BindInfo.
 */
public class DeviceBindInfo {
    private BindInfo bindInfo;

    public DeviceBindInfo(BindInfo bindInfo) {
        this.bindInfo = bindInfo;
    }

    public void setBindInfo(BindInfo bindInfo) {
        this.bindInfo = bindInfo;
    }

    public BindInfo getBindInfo() {
        return bindInfo;
    }

    public long getChannelId() {
        return bindInfo.getChannelId();
    }

    public String getUserId() {
        return bindInfo.getUserId();
    }

    /*
     * Returns: 0: binded online 1: binded offline
     */
    public int getBindStatus() {
        return bindInfo.getBindStatus();
    }

    public long getBindTime() {
        return bindInfo.getBindTime();
    }

    /*
     * Returns: "on": online "off": offline
     */
    public String getOnlineStatus() {
        return bindInfo.getOnlineStatus();
    }

    /*
     * Returns: 1: Browsers 2: PC 3: Android 4: iOS 5: Windows Phone
     */
    public int getDeviceType() {
        return bindInfo.getDeviceType();
    }

    public String getDeviceId() {
        return bindInfo.getDeviceId();
    }

    public long getOnlineTimestamp() {
        return bindInfo.getOnlineTimestamp();
    }

    public long getOnlineExpires() {
        return bindInfo.getOnlineExpires();
    }

}

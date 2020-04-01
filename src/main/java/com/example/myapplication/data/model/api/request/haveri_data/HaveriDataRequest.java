package com.example.myapplication.data.model.api.request.haveri_data;

import com.example.myapplication.data.model.api.request.BaseRequest;

public class HaveriDataRequest extends BaseRequest {

    private String deviceId;
    private String fcm_id;
    private String deviceInfo;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getFcm_id() {
        return fcm_id;
    }

    public void setFcm_id(String fcm_id) {
        this.fcm_id = fcm_id;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }
}

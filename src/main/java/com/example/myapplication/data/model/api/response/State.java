package com.example.myapplication.data.model.api.response;

import java.util.ArrayList;
import java.util.List;

import com.example.myapplication.data.model.api.response.haveri_data.District;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class State {

    @SerializedName("state_id")
    @Expose
    private Integer stateId;
    @SerializedName("state_name_en")
    @Expose
    private String stateNameEn;
    @SerializedName("state_name_kn")
    @Expose
    private String stateNameKn;
    @SerializedName("state_short_code")
    @Expose
    private String stateShortCode;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("is_active")
    @Expose
    private Integer isActive;
    @SerializedName("districts")
    @Expose
    private List<District> districts = null;

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getStateNameEn() {
        return stateNameEn;
    }

    public void setStateNameEn(String stateNameEn) {
        this.stateNameEn = stateNameEn;
    }

    public String getStateNameKn() {
        return stateNameKn;
    }

    public void setStateNameKn(String stateNameKn) {
        this.stateNameKn = stateNameKn;
    }

    public String getStateShortCode() {
        return stateShortCode;
    }

    public void setStateShortCode(String stateShortCode) {
        this.stateShortCode = stateShortCode;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public List<District> getDistricts() {
        if(districts == null) {
            return new ArrayList<>();
        }
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }
}
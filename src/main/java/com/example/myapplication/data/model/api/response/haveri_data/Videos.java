package com.example.myapplication.data.model.api.response.haveri_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Videos implements Serializable {

    @SerializedName("video_link")
    @Expose
    private String vedioUrl;
    @SerializedName("video_title_en")
    @Expose
    private String videoTitleEn;
    @SerializedName("video_title_kn")
    @Expose
    private String videoTitleKn;

    public String getVedioUrl() {
        return vedioUrl;
    }

    public void setVedioUrl(String vedioUrl) {
        this.vedioUrl = vedioUrl;
    }

    public String getVideoTitleEn() {
        return videoTitleEn;
    }

    public void setVideoTitleEn(String videoTitleEn) {
        this.videoTitleEn = videoTitleEn;
    }

    public String getVideoTitleKn() {
        return videoTitleKn;
    }

    public void setVideoTitleKn(String videoTitleKn) {
        this.videoTitleKn = videoTitleKn;
    }
}

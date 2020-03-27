
package com.example.myapplication.data.model.api.response.haveri_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Images implements Serializable {

    @SerializedName("image_link")
    @Expose
    private String imageUrl;
    @SerializedName("image_title_en")
    @Expose
    private String imageTitleEn;
    @SerializedName("image_title_kn")
    @Expose
    private String imageTitleKn;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageTitleEn() {
        return imageTitleEn;
    }

    public void setImageTitleEn(String imageTitleEn) {
        this.imageTitleEn = imageTitleEn;
    }

    public String getImageTitleKn() {
        return imageTitleKn;
    }

    public void setImageTitleKn(String imageTitleKn) {
        this.imageTitleKn = imageTitleKn;
    }
}

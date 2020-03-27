
package com.example.myapplication.data.model.api.response.haveri_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Place implements Serializable {
    @SerializedName("place_id")
    @Expose
    private Integer placeId;
    @SerializedName("taluk_id")
    @Expose
    private Integer talukId;
    @SerializedName("place_name_en")
    @Expose
    private String placeNameEn;
    @SerializedName("place_name_kn")
    @Expose
    private String placeNameKn;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("description_en")
    @Expose
    private String descriptionEn;
    @SerializedName("description_kn")
    @Expose
    private String descriptionKn;
    @SerializedName("time_to_visit_en")
    @Expose
    private String timeToVisitEn;
    @SerializedName("time_to_visit_kn")
    @Expose
    private String timeToVisitKn;
    @SerializedName("contact_phone")
    @Expose
    private String contactPhone;
    @SerializedName("web_link")
    @Expose
    private String webLink;
    @SerializedName("timings")
    @Expose
    private String timings;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("by_road_en")
    @Expose
    private String byRoadEn;
    @SerializedName("by_road_kn")
    @Expose
    private String byRoadKn;
    @SerializedName("by_train_en")
    @Expose
    private String byTrainEn;
    @SerializedName("by_train_kn")
    @Expose
    private String byTrainKn;
    @SerializedName("by_air_en")
    @Expose
    private String byAirEn;
    @SerializedName("by_air_kn")
    @Expose
    private String byAirKn;
    @SerializedName("media_gallery")
    @Expose
    private MediaGallery mediaGallery;

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    public Integer getTalukId() {
        return talukId;
    }

    public void setTalukId(Integer talukId) {
        this.talukId = talukId;
    }

    public String getPlaceNameEn() {
        return placeNameEn;
    }

    public void setPlaceNameEn(String placeNameEn) {
        this.placeNameEn = placeNameEn;
    }

    public String getPlaceNameKn() {
        return placeNameKn;
    }

    public void setPlaceNameKn(String placeNameKn) {
        this.placeNameKn = placeNameKn;
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

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getDescriptionKn() {
        return descriptionKn;
    }

    public void setDescriptionKn(String descriptionKn) {
        this.descriptionKn = descriptionKn;
    }

    public String getTimeToVisitEn() {
        return timeToVisitEn;
    }

    public void setTimeToVisitEn(String timeToVisitEn) {
        this.timeToVisitEn = timeToVisitEn;
    }

    public String getTimeToVisitKn() {
        return timeToVisitKn;
    }

    public void setTimeToVisitKn(String timeToVisitKn) {
        this.timeToVisitKn = timeToVisitKn;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getByRoadEn() {
        return byRoadEn;
    }

    public void setByRoadEn(String byRoadEn) {
        this.byRoadEn = byRoadEn;
    }

    public String getByRoadKn() {
        return byRoadKn;
    }

    public void setByRoadKn(String byRoadKn) {
        this.byRoadKn = byRoadKn;
    }

    public String getByTrainEn() {
        return byTrainEn;
    }

    public void setByTrainEn(String byTrainEn) {
        this.byTrainEn = byTrainEn;
    }

    public String getByTrainKn() {
        return byTrainKn;
    }

    public void setByTrainKn(String byTrainKn) {
        this.byTrainKn = byTrainKn;
    }

    public String getByAirEn() {
        return byAirEn;
    }

    public void setByAirEn(String byAirEn) {
        this.byAirEn = byAirEn;
    }

    public String getByAirKn() {
        return byAirKn;
    }

    public void setByAirKn(String byAirKn) {
        this.byAirKn = byAirKn;
    }

    public MediaGallery getMediaGallery() {
        return mediaGallery;
    }

    public void setMediaGallery(MediaGallery mediaGallery) {
        this.mediaGallery = mediaGallery;
    }
}

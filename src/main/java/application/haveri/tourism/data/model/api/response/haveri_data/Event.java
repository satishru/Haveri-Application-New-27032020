package application.haveri.tourism.data.model.api.response.haveri_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Event implements Serializable {

    @SerializedName("event_id")
    @Expose
    private Integer eventId;
    @SerializedName("taluk_id")
    @Expose
    private Integer talukId;
    @SerializedName("place_id")
    @Expose
    private Integer placeId;
    @SerializedName("event_place_name_en")
    @Expose
    private String eventPlaceNameEn;
    @SerializedName("event_place_name_kn")
    @Expose
    private String eventPlaceNameKn;
    @SerializedName("event_name_en")
    @Expose
    private String eventNameEn;
    @SerializedName("event_name_kn")
    @Expose
    private String eventNameKn;
    @SerializedName("event_address_en")
    @Expose
    private String eventAddressEn;
    @SerializedName("event_address_kn")
    @Expose
    private String eventAddressKn;
    @SerializedName("event_latitude")
    @Expose
    private Double eventLatitude;
    @SerializedName("event_longitude")
    @Expose
    private Double eventLongitude;
    @SerializedName("event_desc_en")
    @Expose
    private String eventDescEn;
    @SerializedName("event_desc_kn")
    @Expose
    private String eventDescKn;
    @SerializedName("event_image")
    @Expose
    private String eventImage;
    @SerializedName("event_video")
    @Expose
    private String eventVideo;
    @SerializedName("event_date_start")
    @Expose
    private String eventDateStart;
    @SerializedName("event_date_end")
    @Expose
    private String eventDateEnd;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("is_active")
    @Expose
    private Integer isActive;
    @SerializedName("taluk_name_en")
    @Expose
    private String talukNameEn;
    @SerializedName("taluk_name_kn")
    @Expose
    private String talukNameKn;
    @SerializedName("place_name_en")
    @Expose
    private String placeNameEn;
    @SerializedName("place_name_kn")
    @Expose
    private String placeNameKn;

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getTalukId() {
        return talukId;
    }

    public void setTalukId(Integer talukId) {
        this.talukId = talukId;
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    public String getEventPlaceNameEn() {
        return eventPlaceNameEn;
    }

    public void setEventPlaceNameEn(String eventPlaceNameEn) {
        this.eventPlaceNameEn = eventPlaceNameEn;
    }

    public String getEventPlaceNameKn() {
        return eventPlaceNameKn;
    }

    public void setEventPlaceNameKn(String eventPlaceNameKn) {
        this.eventPlaceNameKn = eventPlaceNameKn;
    }

    public String getEventNameEn() {
        return eventNameEn;
    }

    public void setEventNameEn(String eventNameEn) {
        this.eventNameEn = eventNameEn;
    }

    public String getEventNameKn() {
        return eventNameKn;
    }

    public void setEventNameKn(String eventNameKn) {
        this.eventNameKn = eventNameKn;
    }

    public String getEventAddressEn() {
        if(eventAddressEn == null) {
            return "";
        }
        return eventAddressEn;
    }

    public void setEventAddressEn(String eventAddressEn) {
        this.eventAddressEn = eventAddressEn;
    }

    public String getEventAddressKn() {
        if(eventAddressKn == null) {
            return "";
        }
        return eventAddressKn;
    }

    public void setEventAddressKn(String eventAddressKn) {
        this.eventAddressKn = eventAddressKn;
    }

    public Double getEventLatitude() {
        if(eventLatitude == null) {
            return 0.0;
        }
        return eventLatitude;
    }

    public void setEventLatitude(Double eventLatitude) {
        this.eventLatitude = eventLatitude;
    }

    public Double getEventLongitude() {
        if(eventLongitude == null) {
            return 0.0;
        }
        return eventLongitude;
    }

    public void setEventLongitude(Double eventLongitude) {
        this.eventLongitude = eventLongitude;
    }

    public String getEventDescEn() {
        return eventDescEn;
    }

    public void setEventDescEn(String eventDescEn) {
        this.eventDescEn = eventDescEn;
    }

    public String getEventDescKn() {
        return eventDescKn;
    }

    public void setEventDescKn(String eventDescKn) {
        this.eventDescKn = eventDescKn;
    }

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }

    public String getEventVideo() {
        return eventVideo;
    }

    public void setEventVideo(String eventVideo) {
        this.eventVideo = eventVideo;
    }

    public String getEventDateStart() {
        return eventDateStart;
    }

    public void setEventDateStart(String eventDateStart) {
        this.eventDateStart = eventDateStart;
    }

    public String getEventDateEnd() {
        return eventDateEnd;
    }

    public void setEventDateEnd(String eventDateEnd) {
        this.eventDateEnd = eventDateEnd;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getTalukNameEn() {
        return talukNameEn;
    }

    public void setTalukNameEn(String talukNameEn) {
        this.talukNameEn = talukNameEn;
    }

    public String getTalukNameKn() {
        return talukNameKn;
    }

    public void setTalukNameKn(String talukNameKn) {
        this.talukNameKn = talukNameKn;
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
}
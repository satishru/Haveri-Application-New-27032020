package application.haveri.tourism.data.model.api.response.haveri_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class District implements Serializable {

    @SerializedName("district_id")
    @Expose
    private Integer districtId;
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
    @SerializedName("district_name_en")
    @Expose
    private String districtNameEn;
    @SerializedName("district_name_kn")
    @Expose
    private String districtNameKn;
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
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("events")
    @Expose
    private List<Event> events = null;
    @SerializedName("taluks")
    @Expose
    private List<Taluk> taluks = null;

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

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

    public String getDistrictNameEn() {
        return districtNameEn;
    }

    public void setDistrictNameEn(String districtNameEn) {
        this.districtNameEn = districtNameEn;
    }

    public String getDistrictNameKn() {
        return districtNameKn;
    }

    public void setDistrictNameKn(String districtNameKn) {
        this.districtNameKn = districtNameKn;
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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public List<Event> getEvents() {
        if (events == null) {
            return new ArrayList<>();
        }
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Taluk> getTaluks() {
        if (taluks == null) {
            return new ArrayList<>();
        }
        List<Taluk> formattedList = new ArrayList<>();
        for (Taluk taluk : taluks) {
            if (taluk.getPlaces().size() > 0)
                formattedList.add(taluk);
        }
        return formattedList;
    }

    public void setTaluks(List<Taluk> taluks) {
        this.taluks = taluks;
    }
}

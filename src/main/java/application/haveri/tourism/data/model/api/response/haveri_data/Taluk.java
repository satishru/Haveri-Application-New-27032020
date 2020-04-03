package application.haveri.tourism.data.model.api.response.haveri_data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Taluk implements Serializable {

    @SerializedName("taluk_id")
    @Expose
    private Integer talukId;
    @SerializedName("district_id")
    @Expose
    private Integer districtId;
    @SerializedName("taluk_name_en")
    @Expose
    private String talukNameEn;
    @SerializedName("taluk_name_kn")
    @Expose
    private String talukNameKn;
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
    @SerializedName("places")
    @Expose
    private List<Place> places = null;

    public Integer getTalukId() {
        return talukId;
    }

    public void setTalukId(Integer talukId) {
        this.talukId = talukId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
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

    public List<Place> getPlaces() {
        if(places == null) {
            return new ArrayList<>();
        }
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }
}

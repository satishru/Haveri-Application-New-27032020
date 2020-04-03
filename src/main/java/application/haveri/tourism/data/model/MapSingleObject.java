package application.haveri.tourism.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MapSingleObject implements Parcelable {

    private String titleEn;
    private String titleKn;
    private double latitude;
    private double longitude;

    public MapSingleObject() {
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getTitleKn() {
        return titleKn;
    }

    public void setTitleKn(String titleKn) {
        this.titleKn = titleKn;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private MapSingleObject(Parcel in) {
        titleEn = in.readString();
        titleKn = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    public static final Creator<MapSingleObject> CREATOR = new Creator<MapSingleObject>() {
        @Override
        public MapSingleObject createFromParcel(Parcel in) {
            return new MapSingleObject(in);
        }

        @Override
        public MapSingleObject[] newArray(int size) {
            return new MapSingleObject[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titleEn);
        dest.writeString(titleKn);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }
}

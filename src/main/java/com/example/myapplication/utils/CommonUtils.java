package com.example.myapplication.utils;

import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.text.Html;
import android.util.Base64;

import androidx.annotation.Nullable;

import com.example.myapplication.BuildConfig;
import com.example.myapplication.HaveriApplication;
import com.example.myapplication.data.model.api.response.haveri_data.District;
import com.example.myapplication.data.model.api.response.haveri_data.Event;
import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public final class CommonUtils {

    private final static String CHAR_SET_NAME = "UTF-8";

    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    public static String toHtml(String item) {
        if (checkNullOrEmpty(item)) {
            return "";
        }
        return Html.fromHtml(item.trim()).toString().trim();
    }

    public static boolean isValidCoordinates(Double lat, Double lang) {
        return (lat != null && lang != null) && (lat > 0 && lang > 0);
    }

    private static Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat(AppConstants.DATE_FORMAT_YYYY_MM_DD,
                    Locale.ENGLISH).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String formatDate(Date date) {
        if (date != null) {
            return new SimpleDateFormat(AppConstants.DATE_FORMAT_DD_MMM_YYYY,
                    Locale.ENGLISH).format(date);
        }
        return "";
    }

    private static Date getDateWithoutTime() {
        SimpleDateFormat formatter = new SimpleDateFormat(AppConstants.DATE_FORMAT_YYYY_MM_DD,
                Locale.ENGLISH);
        try {
            return parseDate(formatter.format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isDateGreaterOrEqualToday(String eventEndDateStr) {
        Date todayDate = getDateWithoutTime();
        Date eventEndDate = parseDate(eventEndDateStr);
        if (eventEndDate != null && todayDate != null) {
            return eventEndDate.compareTo(todayDate) > -1;
        }
        return false;
    }

    public static String formatEventDate(String dateStr1, String dateStr2) {
        String formatted_date = dateStr1 + "-" + dateStr2;
        Date date1 = parseDate(dateStr1);
        Date date2 = parseDate(dateStr2);
        if (date1 != null && date2 != null) {
            if (date1.compareTo(date2) == 0) {
                formatted_date = formatDate(date1);
            } else {
                formatted_date = formatDate(date1) + " - " + formatDate(date2);
            }
        }
        return formatted_date;
    }

    public static String convertObjToJson(Object object) {
        if (object == null) {
            return "";
        }
        return new Gson().toJson(object);
    }

    public static <T> T convertJsonToObj(String json, Class<T> objClass) {
        try {
            return new Gson().fromJson(json, objClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String stringToBase64(final String json) {
        if (checkNullOrEmpty(json)) {
            return "";
        }
        try {
            return Base64.encodeToString(json.getBytes(CHAR_SET_NAME), Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String base64ToString(String base64) throws UnsupportedEncodingException {
        if (checkNullOrEmpty(base64)) {
            return "";
        }
        return new String(Base64.decode(base64, Base64.DEFAULT), CHAR_SET_NAME);
    }

    public static boolean checkNullOrEmpty(@Nullable CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static String getDistance(double latitude, double longitude, Location src) {
        String distanceStr = "";
        double distance = distance(latitude, longitude, src);
        if (distance >= 0) {
            return convertDecimalFormat(distance) + " Km";
        }
        return distanceStr;
    }

    private static double distance(double latitude, double longitude, Location src) {
        if (src != null && latitude > 0 && longitude > 0) {

            Location src_location = new Location(LocationManager.GPS_PROVIDER);
            src_location.setLatitude(src.getLatitude());
            src_location.setLongitude(src.getLongitude());

            Location dest_location = new Location(LocationManager.GPS_PROVIDER);
            dest_location.setLatitude(latitude);
            dest_location.setLongitude(longitude);

            AppLogger.d("DISTANCE 1 %s", src.distanceTo(dest_location));
            AppLogger.d("DISTANCE 2 %s", dest_location.distanceTo(src));

            //float distance = src.distanceTo(dest_location); // in meters
            return src_location.distanceTo(dest_location) / 1000; // in km
        }
        return -1;
    }

    /**
     * Converts float to string value with two digits after decimal
     *
     * @param val Value
     * @return Formatted value
     */
    private static String convertDecimalFormat(double val) {
        DecimalFormat formatter = new DecimalFormat("##,###0.00");
        String value = formatter.format(val);
        if (value.endsWith(".00")) {
            return value.replace(".00", "");
        }
        if (value.endsWith(".0")) {
            return value.replace(".0", "");
        }
        value = value.replace(",", "");
        return value;
    }

    /**
     * Counts the number of events in taluk
     *
     * @param selectedTaluk Taluk
     * @return talukEventCount
     */
    public static int getTalukEventCount(Taluk selectedTaluk) {
        return getEventList(selectedTaluk, null).size();
    }

    /**
     * Counts the number of events in place
     *
     * @param selectedPlace Place
     * @return placeEventCount
     */
    public static int getPlaceEventCount(Place selectedPlace) {
        return getEventList(null, selectedPlace).size();
    }

    /**
     * Returns the Taluk Event List
     *
     * @return allEventList
     */
    public static List<Event> getAllEventList() {
        return getEventList(null, null);
    }

    /**
     * Returns the Taluk Event List
     *
     * @param selectedTaluk Taluk
     * @return talukEventList
     */
    public static List<Event> getTalukEventList(Taluk selectedTaluk) {
        return getEventList(selectedTaluk, null);
    }

    /**
     * Returns the Place Event List
     *
     * @param selectedPlace Place
     * @return placeEventList
     */
    public static List<Event> getPlaceEventList(Place selectedPlace) {
        return getEventList(null, selectedPlace);
    }

    /**
     * Returns the Event List
     *
     * @param selectedTaluk Taluk
     * @return eventList
     */
    private static List<Event> getEventList(Taluk selectedTaluk, Place selectedPlace) {
        List<Event> eventList = new ArrayList<>();
        District district = HaveriApplication.getInstance().getDistrict();
        if (district != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                if (selectedTaluk != null) {
                    // Event list for Taluk
                    eventList = district.getEvents().stream().filter(e ->
                            e.getTalukId().equals(selectedTaluk.getTalukId()) &&
                                    CommonUtils.isDateGreaterOrEqualToday(
                                            e.getEventDateEnd())).collect(
                            Collectors.toList());
                } else if (selectedPlace != null) {
                    // Event list for Place
                    eventList = district.getEvents().stream().filter(e ->
                            e.getPlaceId().equals(selectedPlace.getPlaceId()) &&
                                    CommonUtils.isDateGreaterOrEqualToday(
                                            e.getEventDateEnd())).collect(
                            Collectors.toList());
                } else {
                    // All event list
                    eventList = district.getEvents().stream().filter(e ->
                            CommonUtils.isDateGreaterOrEqualToday(
                                    e.getEventDateEnd())).collect(
                            Collectors.toList());
                }
            } else {
                for (Event event : district.getEvents()) {
                    if (selectedTaluk != null && event.getTalukId().equals(
                            selectedTaluk.getTalukId()) &&
                            CommonUtils.isDateGreaterOrEqualToday(event.getEventDateEnd())) {
                        eventList.add(event);
                    } else if (selectedPlace != null && event.getPlaceId().equals(
                            selectedPlace.getPlaceId()) &&
                            CommonUtils.isDateGreaterOrEqualToday(event.getEventDateEnd())) {
                        eventList.add(event);
                    } else if (CommonUtils.isDateGreaterOrEqualToday(event.getEventDateEnd())) {
                        eventList.add(event);
                    }
                }
            }
        }
        return eventList;
    }

    public static Taluk getTalukUsingPlace(Place place) {
        Taluk taluk = null;
        District district = HaveriApplication.getInstance().getDistrict();
        if (district != null) {
            for (Taluk talukObj : district.getTaluks()) {
                if(place.getTalukId().equals(talukObj.getTalukId())) {
                    taluk =  talukObj;
                    break;
                }
            }
        }
        return taluk;
    }

    /**
     * This method is to create mock list during development if list size is less or =2
     *
     * @param list          Original List
     * @param increase_size Increase list size
     * @return mock list
     */
    public static <E> List<E> mockList(List<E> list, int increase_size) {
        try {
            if (BuildConfig.DEBUG) {
                if (list.size() > 0 && list.size() <= 2 && list.get(0) != null) {
                    List<E> mockList = new ArrayList<>(list);
                    E ele = list.get(0);
                    for (int i = 0; i < increase_size; i++) {
                        mockList.add(ele);
                    }
                    return mockList;
                }
                return list;
            }
            return list;
        } catch (Exception e) {
            return list;
        }
    }
}

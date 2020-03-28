package com.example.myapplication.utils;

public final class AppConstants {

    private AppConstants() {
        // This utility class is not publicly instantiable
    }

    public static final long NULL_INDEX = -1L;
    public static final int MOCK_LIST_SIZE = 10;

    public static final int LIST_SPACING_ITEM_MARGIN = 12;

    public static final int LOCATION_UPDATE_TIME = 15; // Seconds
    public static final int LOCATION_UPDATE_DISTANCE = 1;  // Meter

    static final String DATE_FORMAT_YYYY_MM_DD  = "yyyy-dd-MM"; // Api Format
    static final String DATE_FORMAT_DD_MMM_YYYY = "dd MMM yyyy";

    public static final String SPLASH_IMAGE = "https://www.dropbox.com/s/ob7bzo3ux3c89tj/splash_bg.png?raw=1";

    // Pref Constants
    public static final String PREF_NAME = "haveri_tourism_pref";

    // DB Constants
    public static final String DB_NAME = "haveri_tourism.db";
    public static final String DB_TABLE_DATA = "haveri_data";

    // Request Code Constants
    public static final int REQUEST_CODE_LOCATION_TURN_ON = 1001;
    public static final int REQUEST_CODE_LOCATION_PERMISSION = 1002;

    // Intent/Bundle Constants
    public static final String INTENT_ERROR_ICON = "INTENT_ERROR_ICON";
    public static final String INTENT_ERROR_MESSAGE = "INTENT_ERROR_MESSAGE";
    public static final String INTENT_MAP_SINGLE = "INTENT_MAP_SINGLE";
    public static final String INTENT_HAVERI_DATA = "INTENT_HAVERI_DATA";
    public static final String INTENT_SELECTED_TALUK = "INTENT_SELECTED_TALUK";
    public static final String INTENT_SELECTED_EVENT = "INTENT_SELECTED_EVENT";
    public static final String INTENT_IMAGE_LIST = "INTENT_IMAGE_LIST";
    public static final String INTENT_SELECTED_IMAGE = "INTENT_SELECTED_IMAGE";
    public static final String INTENT_SELECTED_VIDEO = "INTENT_SELECTED_VIDEO";
    public static final String INTENT_SELECTED_PLACE = "INTENT_SELECTED_PLACE";
}

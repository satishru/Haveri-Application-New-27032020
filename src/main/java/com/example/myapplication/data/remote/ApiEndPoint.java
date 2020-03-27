package com.example.myapplication.data.remote;

import com.example.myapplication.BuildConfig;

public final class ApiEndPoint {

    private ApiEndPoint() {
        // This class is not publicly instantiable
    }

    private static String makeUrl(String endPoint) {
        return BuildConfig.BASE_URL + endPoint;
    }

    public static final String YOUTUBE_THUMB_URL = "https://img.youtube.com/vi/%s/0.jpg";
    public static final String YOUTUBE_WATCH_URL = "https://www.youtube.com/watch?v=%s";

    //https://api.myjson.com/bins/sd09o?pretty=1
    public static final String ENDPOINT_GET_DATA  = makeUrl("bins/sd09o?pretty=1");
    public static final String ENDPOINT_ON_BOARD  = makeUrl("bins/sd09o");
}

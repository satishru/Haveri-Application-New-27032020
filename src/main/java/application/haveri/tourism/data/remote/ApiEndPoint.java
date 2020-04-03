package application.haveri.tourism.data.remote;

import application.haveri.tourism.BuildConfig;

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
    //https://jsonblob.com/api/jsonBlob/f0705f8c-7593-11ea-9538-b9fc35d8d200
    //https://api.npoint.io/cd3edc5b383607c2ccf3
    public static final String ENDPOINT_GET_DATA  = makeUrl("cd3edc5b383607c2ccf3");
    public static final String ENDPOINT_ON_BOARD  = makeUrl("bins/sd09o");
}

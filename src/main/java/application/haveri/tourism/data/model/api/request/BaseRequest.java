package application.haveri.tourism.data.model.api.request;

import com.google.gson.Gson;

import application.haveri.tourism.BuildConfig;
import application.haveri.tourism.HaveriApplication;
import application.haveri.tourism.utils.AppUtils;

public class BaseRequest {
    private String api_key = BuildConfig.API_KEY;
    private String device_id = AppUtils.getDeviceId(HaveriApplication.getInstance());
    private String device_info = new Gson().toJson(new DeviceInfo());
    private String access_token;
    private Long user_id;
}

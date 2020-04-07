package application.haveri.tourism.data.model.api.request.haveri_data;

import application.haveri.tourism.data.model.api.request.BaseRequest;

public class HaveriDataRequest extends BaseRequest {

    private String fcm_token;

    public void setFcm_token(String fcm_token) {
        this.fcm_token = fcm_token;
    }
}

package application.haveri.tourism.data.remote;

import application.haveri.tourism.data.model.api.request.haveri_data.HaveriDataRequest;
import application.haveri.tourism.data.model.api.response.BaseResponse;

import io.reactivex.Single;

public interface ApiHelper {

    ApiHeader getApiHeader();

    Single<BaseResponse> doCallHaveriDataApiCall(HaveriDataRequest request);
}

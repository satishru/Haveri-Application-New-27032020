package com.example.myapplication.data.remote;

import com.example.myapplication.data.model.api.request.haveri_data.HaveriDataRequest;
import com.example.myapplication.data.model.api.response.BaseResponse;
import com.example.myapplication.data.model.api.response.haveri_data.District;

import io.reactivex.Single;

public interface ApiHelper {

    ApiHeader getApiHeader();

    Single<BaseResponse> doCallHaveriDataApiCall(HaveriDataRequest request);
}

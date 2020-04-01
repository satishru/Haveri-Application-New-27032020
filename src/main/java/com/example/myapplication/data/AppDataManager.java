package com.example.myapplication.data;

import android.content.Context;

import com.example.myapplication.data.local.db.DbHelper;
import com.example.myapplication.data.local.prefs.PreferencesHelper;
import com.example.myapplication.data.model.api.request.haveri_data.HaveriDataRequest;
import com.example.myapplication.data.model.api.response.BaseResponse;
import com.example.myapplication.data.remote.ApiHeader;
import com.example.myapplication.data.remote.ApiHelper;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class AppDataManager extends PreAppDataManager {

    @Inject
    AppDataManager(Context context, DbHelper dbHelper,
                          PreferencesHelper preferencesHelper, ApiHelper apiHelper, Gson gson) {
        super(context,dbHelper,preferencesHelper,apiHelper,gson);
    }

    /**
     * ApiHelper Call Backs
     */
    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }

    @Override
    public Single<BaseResponse> doCallHaveriDataApiCall(HaveriDataRequest request) {
        return mApiHelper.doCallHaveriDataApiCall(request);
    }
    /* ApiHelper Call Backs Ends */
}

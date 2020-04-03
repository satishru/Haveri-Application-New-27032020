package application.haveri.tourism.data;

import android.content.Context;

import application.haveri.tourism.data.local.db.DbHelper;
import application.haveri.tourism.data.local.prefs.PreferencesHelper;
import application.haveri.tourism.data.model.api.request.haveri_data.HaveriDataRequest;
import application.haveri.tourism.data.model.api.response.BaseResponse;
import application.haveri.tourism.data.remote.ApiHeader;
import application.haveri.tourism.data.remote.ApiHelper;
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

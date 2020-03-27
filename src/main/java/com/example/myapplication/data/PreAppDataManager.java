package com.example.myapplication.data;

import android.content.Context;

import com.example.myapplication.data.local.db.DbHelper;
import com.example.myapplication.data.local.prefs.PreferencesHelper;
import com.example.myapplication.data.model.db.HaveriData;
import com.example.myapplication.data.remote.ApiHelper;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.Observable;

public abstract class PreAppDataManager implements DataManager {
    protected final ApiHelper mApiHelper;

    protected final Context mContext;

    protected final DbHelper mDbHelper;

    protected final Gson mGson;

    protected final PreferencesHelper mPreferencesHelper;

    public PreAppDataManager(Context context, DbHelper dbHelper,
                             PreferencesHelper preferencesHelper, ApiHelper apiHelper, Gson gson) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
        mGson = gson;
    }

    /**
     * DbHelper Call Backs
     *
     * @return
     */
    @Override
    public Observable<List<HaveriData>> getHaveriData() {
        return mDbHelper.getHaveriData();
    }

    @Override
    public Observable<Boolean> isHaveriDataEmpty() {
        return mDbHelper.isHaveriDataEmpty();
    }

    @Override
    public Observable<Boolean> deleteHaveriData() {
        return mDbHelper.deleteHaveriData();
    }

    @Override
    public Observable<Boolean> insertHaveriData(HaveriData data) {
        return mDbHelper.insertHaveriData(data);
    }
    /**
     * DbHelper Call Backs Ends
     */

    /**
     * DataManager Call Backs
     */
    @Override
    public Observable<Boolean> seedHaveriData() {
        return mDbHelper.isHaveriDataEmpty();
    }

    @Override
    public void updateApiHeader(Long userId, String accessToken) {
        mApiHelper.getApiHeader().getProtectedApiHeader().setUserId(userId);
        mApiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

    @Override
    public void updateUserInfo(
            String accessToken,
            Long userId,
            String userName,
            String email) {

        setAccessToken(accessToken);
        setCurrentUserId(userId);
        updateApiHeader(userId, accessToken);
    }
    /**
     * DataManager Call Backs Ends
     */

    /**
     * PreferencesHelper Call Backs
     */
    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);
        mApiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

    @Override
    public Long getCurrentUserId() {
        return mPreferencesHelper.getCurrentUserId();
    }

    @Override
    public void setCurrentUserId(Long userId) {
        mPreferencesHelper.setCurrentUserId(userId);
    }

    @Override
    public void setSelectedLanguage(int language) {
        mPreferencesHelper.setSelectedLanguage(language);
    }

    @Override
    public int getSelectedLanguage() {
        return mPreferencesHelper.getSelectedLanguage();
    }

    @Override
    public void setIsScreenReloadRequired(boolean isScreenReloadRequired) {
        mPreferencesHelper.setIsScreenReloadRequired(isScreenReloadRequired);
    }

    @Override
    public boolean isScreenReloadRequired() {
        return mPreferencesHelper.isScreenReloadRequired();
    }

    @Override
    public void setSelectedTheme(int selectedTheme) {
        mPreferencesHelper.setSelectedTheme(selectedTheme);
    }

    @Override
    public int getSelectedTheme() {
        return mPreferencesHelper.getSelectedTheme();
    }
    /**
     * PreferencesHelper Call Backs Ends
     */
}

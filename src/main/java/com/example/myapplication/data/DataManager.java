package com.example.myapplication.data;

import com.example.myapplication.data.local.db.DbHelper;
import com.example.myapplication.data.local.prefs.PreferencesHelper;
import com.example.myapplication.data.remote.ApiHelper;

import io.reactivex.Observable;

public interface DataManager extends DbHelper,PreferencesHelper, ApiHelper {

    Observable<Boolean> seedHaveriData();

    void updateApiHeader(Long userId, String accessToken);

    void updateUserInfo(
            String accessToken,
            Long userId,
            String userName,
            String email);
}

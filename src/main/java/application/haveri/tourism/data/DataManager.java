package application.haveri.tourism.data;

import application.haveri.tourism.data.local.db.DbHelper;
import application.haveri.tourism.data.local.prefs.PreferencesHelper;
import application.haveri.tourism.data.remote.ApiHelper;

import io.reactivex.Observable;

public interface DataManager extends DbHelper, PreferencesHelper, ApiHelper {

    Observable<Boolean> seedHaveriData();

    void updateApiHeader(Long userId, String accessToken);

    void updateUserInfo(
            String accessToken,
            Long userId,
            String userName,
            String email);
}

package application.haveri.tourism.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import application.haveri.tourism.di.PreferenceInfo;
import application.haveri.tourism.utils.AppConstants;
import application.haveri.tourism.utils.Language;
import application.haveri.tourism.utils.ScreenUtils;

import javax.inject.Inject;

public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";
    private static final String PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID";
    private static final String PREF_KEY_SELECTED_LANGUAGE = "PREF_KEY_SELECTED_LANGUAGE";
    private static final String PREF_KEY_THEME_COLOR = "PREF_KEY_THEME_COLOR";
    private static final String PREF_KEY_IS_RELOAD_SCREEN = "PREF_KEY_IS_RELOAD_SCREEN";

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(Context context, @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public String getAccessToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }

    @Override
    public Long getCurrentUserId() {
        long userId = mPrefs.getLong(PREF_KEY_CURRENT_USER_ID, AppConstants.NULL_INDEX);
        return userId == AppConstants.NULL_INDEX ? null : userId;
    }

    @Override
    public void setCurrentUserId(Long userId) {
        long id = userId == null ? AppConstants.NULL_INDEX : userId;
        mPrefs.edit().putLong(PREF_KEY_CURRENT_USER_ID, id).apply();
    }

    @Override
    public void setSelectedLanguage(int language) {
        mPrefs.edit().putInt(PREF_KEY_SELECTED_LANGUAGE, language).apply();
    }

    @Override
    public int getSelectedLanguage() {
        return mPrefs.getInt(PREF_KEY_SELECTED_LANGUAGE, Language.EN.getValue());
    }

    @Override
    public void setIsScreenReloadRequired(boolean isScreenReloadRequired) {
        mPrefs.edit().putBoolean(PREF_KEY_IS_RELOAD_SCREEN, isScreenReloadRequired).apply();
    }

    @Override
    public boolean isScreenReloadRequired() {
        return mPrefs.getBoolean(PREF_KEY_IS_RELOAD_SCREEN, false);
    }

    @Override
    public void setSelectedTheme(int selectedTheme) {
        mPrefs.edit().putInt(PREF_KEY_THEME_COLOR, selectedTheme).apply();
    }

    @Override
    public int getSelectedTheme() {
        return mPrefs.getInt(PREF_KEY_THEME_COLOR, ScreenUtils.THEME_ID_DarkPurple);
    }
}

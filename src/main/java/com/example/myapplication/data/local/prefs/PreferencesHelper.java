package com.example.myapplication.data.local.prefs;

public interface PreferencesHelper {

    String getAccessToken();

    void setAccessToken(String accessToken);

    Long getCurrentUserId();

    void setCurrentUserId(Long userId);

    void setSelectedLanguage(int language);

    int getSelectedLanguage();

    void setIsScreenReloadRequired(boolean isScreenReloadRequired);

    boolean isScreenReloadRequired();

    void setSelectedTheme(int selectedTheme);

    int getSelectedTheme();

}

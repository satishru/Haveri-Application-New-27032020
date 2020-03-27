package com.example.myapplication.ui.activity.setting;

import com.example.myapplication.ui.base.BaseNavigator;
import com.example.myapplication.utils.Language;

public interface iSettingActivityContract {

    interface iSettingActivityNavigator extends BaseNavigator {
        void setLocale(int selectedLanguage);
        void clearImageCache();
    }

    interface iSettingActivityViewModel {
        void startInit();
        void onLanguageClicked(Language language);
        void clearImageCache();
    }
}

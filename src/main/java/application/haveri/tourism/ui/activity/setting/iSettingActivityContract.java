package application.haveri.tourism.ui.activity.setting;

import application.haveri.tourism.ui.base.BaseNavigator;
import application.haveri.tourism.utils.Language;

public interface iSettingActivityContract {

    interface iSettingActivityNavigator extends BaseNavigator {
        void setLocale(int selectedLanguage);
        void clearImageCache();
        void openPlayStore();
        void openAboutActivity();
    }

    interface iSettingActivityViewModel {
        void startInit();
        void onLanguageClicked(Language language);
        void clearImageCache();
        void onFeedBackClicked();
        void onAboutClicked();
    }
}

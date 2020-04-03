package application.haveri.tourism.ui.activity.splash;

import application.haveri.tourism.ui.base.BaseNavigator;

public interface iSplashActivityContract {
    interface iSplashNavigator extends BaseNavigator {
        void openHomeActivity();
        void openErrorDialog(int drawable, String errorMessage);
        boolean isNetWorkConnected();
    }

    interface iSplashViewModel {
        void startLoading();
    }
}

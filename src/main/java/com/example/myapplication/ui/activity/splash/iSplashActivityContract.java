package com.example.myapplication.ui.activity.splash;

import com.example.myapplication.ui.base.BaseNavigator;

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

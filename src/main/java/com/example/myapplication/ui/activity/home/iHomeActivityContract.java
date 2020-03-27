package com.example.myapplication.ui.activity.home;

import com.example.myapplication.ui.base.BaseNavigator;

public interface iHomeActivityContract {
    interface iHomeActivityNavigator extends BaseNavigator {
        void onBackIconClicked();
    }

    interface iHomeActivityViewModel {
        void onBackIconClicked();
    }
}

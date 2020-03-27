package com.example.myapplication.ui.activity.media.image;

import com.example.myapplication.ui.base.BaseNavigator;

class iImageViewActivityContract {

    interface iImageViewActivityNavigator extends BaseNavigator {
        void closeActivity();
    }

    interface iImageViewActivityViewModel {
        void onBackIconClicked();
    }
}

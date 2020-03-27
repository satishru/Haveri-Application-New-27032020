package com.example.myapplication.ui.activity.media.video;

import com.example.myapplication.ui.base.BaseNavigator;

public interface iVideosExploreActivityContract {
    interface iVideosExploreActivityNavigator extends BaseNavigator {
        boolean isSingleVideoShow();

        void loadVideoListFragment();

        void loadVideoShowFragment();
    }

    interface iVideosExploreActivityViewModel {
        void startLoadingData();
    }
}

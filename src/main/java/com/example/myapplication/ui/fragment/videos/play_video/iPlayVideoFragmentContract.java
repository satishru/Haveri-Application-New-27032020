package com.example.myapplication.ui.fragment.videos.play_video;

import com.example.myapplication.ui.base.BaseNavigator;

public interface iPlayVideoFragmentContract {
    interface iPlayVideoFragmentNavigator extends BaseNavigator {
        void onBackIconClicked();
    }

    interface iPlayVideoFragmentViewModel {
        void onBackIconClicked();
    }
}

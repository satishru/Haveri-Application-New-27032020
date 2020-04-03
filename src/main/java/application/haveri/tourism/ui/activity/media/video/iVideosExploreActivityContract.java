package application.haveri.tourism.ui.activity.media.video;

import application.haveri.tourism.ui.base.BaseNavigator;

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

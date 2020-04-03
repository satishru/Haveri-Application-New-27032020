package application.haveri.tourism.ui.fragment.videos.play_video;

import application.haveri.tourism.ui.base.BaseNavigator;

public interface iPlayVideoFragmentContract {
    interface iPlayVideoFragmentNavigator extends BaseNavigator {
        void onBackIconClicked();
    }

    interface iPlayVideoFragmentViewModel {
        void onBackIconClicked();
    }
}

package application.haveri.tourism.ui.activity.media.video;

import application.haveri.tourism.data.DataManager;
import application.haveri.tourism.ui.base.BaseViewModel;
import application.haveri.tourism.utils.rx.SchedulerProvider;

public class VideosExploreActivityViewModel extends BaseViewModel<iVideosExploreActivityContract.iVideosExploreActivityNavigator>
        implements iVideosExploreActivityContract.iVideosExploreActivityViewModel {

    public VideosExploreActivityViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void startLoadingData() {
        if (getNavigator().isSingleVideoShow()) {
            getNavigator().loadVideoShowFragment();
        } else {
            getNavigator().loadVideoListFragment();
        }
    }
}

package com.example.myapplication.ui.activity.media.video;

import com.example.myapplication.data.DataManager;
import com.example.myapplication.ui.base.BaseViewModel;
import com.example.myapplication.utils.rx.SchedulerProvider;

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

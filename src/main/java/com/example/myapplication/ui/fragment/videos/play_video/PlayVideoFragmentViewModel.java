package com.example.myapplication.ui.fragment.videos.play_video;

import com.example.myapplication.data.DataManager;
import com.example.myapplication.ui.base.BaseViewModel;
import com.example.myapplication.utils.rx.SchedulerProvider;

public class PlayVideoFragmentViewModel extends BaseViewModel<iPlayVideoFragmentContract.iPlayVideoFragmentNavigator> implements
        iPlayVideoFragmentContract.iPlayVideoFragmentViewModel {

    public PlayVideoFragmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void onBackIconClicked() {
        getNavigator().onBackIconClicked();
    }
}

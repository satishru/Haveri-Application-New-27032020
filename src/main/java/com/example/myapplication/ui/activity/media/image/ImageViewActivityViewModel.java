package com.example.myapplication.ui.activity.media.image;

import com.example.myapplication.data.DataManager;
import com.example.myapplication.ui.base.BaseViewModel;
import com.example.myapplication.utils.rx.SchedulerProvider;

public class ImageViewActivityViewModel extends BaseViewModel<iImageViewActivityContract.iImageViewActivityNavigator> implements
        iImageViewActivityContract.iImageViewActivityViewModel {

    public ImageViewActivityViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void onBackIconClicked() {
        getNavigator().closeActivity();
    }
}

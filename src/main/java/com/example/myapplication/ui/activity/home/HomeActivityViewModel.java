package com.example.myapplication.ui.activity.home;

import com.example.myapplication.data.DataManager;
import com.example.myapplication.ui.base.BaseViewModel;
import com.example.myapplication.utils.rx.SchedulerProvider;

public class HomeActivityViewModel extends BaseViewModel<iHomeActivityContract.iHomeActivityNavigator> implements
        iHomeActivityContract.iHomeActivityViewModel{

    public HomeActivityViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void onBackIconClicked() {
        getNavigator().onBackIconClicked();
    }
}

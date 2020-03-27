package com.example.myapplication.ui.activity.map;

import com.example.myapplication.data.DataManager;
import com.example.myapplication.ui.base.BaseNavigator;
import com.example.myapplication.ui.base.BaseViewModel;
import com.example.myapplication.utils.rx.SchedulerProvider;

public class MapSingleActivityViewModel extends BaseViewModel<BaseNavigator> {

    public MapSingleActivityViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}

package com.example.myapplication.ui.fragment.taluk.taluk_detail.about;

import com.example.myapplication.data.DataManager;
import com.example.myapplication.ui.base.BaseViewModel;
import com.example.myapplication.utils.rx.SchedulerProvider;

public class TalukAboutFragmentViewModel extends BaseViewModel<iTalukAboutFragmentContract.iTalukAboutFragmentNavigator> implements
        iTalukAboutFragmentContract.iTalukAboutFragmentViewModel {

    public TalukAboutFragmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}

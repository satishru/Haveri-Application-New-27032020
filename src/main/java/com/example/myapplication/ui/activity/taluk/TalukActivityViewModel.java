package com.example.myapplication.ui.activity.taluk;

import com.example.myapplication.data.DataManager;
import com.example.myapplication.data.model.api.response.haveri_data.District;
import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.ui.base.BaseViewModel;
import com.example.myapplication.utils.AppConstants;
import com.example.myapplication.utils.CommonUtils;
import com.example.myapplication.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

public class TalukActivityViewModel extends BaseViewModel<iTalukActivityContract.iTalukActivityNavigator> implements
        iTalukActivityContract.iTalukActivityViewModel {

    public TalukActivityViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void startLoadingData() {
        if (getNavigator().isSingleTalukShow()) {
            getNavigator().loadTalukDetailFragment();
        } else {
            getNavigator().loadTalukListFragment();
        }
    }

    @Override
    public List<Taluk> getTalukList(District district) {
        List<Taluk> talukList = new ArrayList<>();
        if (district != null) {
            talukList.addAll(CommonUtils.mockList(new ArrayList<>(district.getTaluks()), AppConstants.MOCK_LIST_SIZE));
        }
        return talukList;
    }
}

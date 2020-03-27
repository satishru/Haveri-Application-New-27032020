package com.example.myapplication.ui.activity.taluk;

import com.example.myapplication.data.model.api.response.haveri_data.District;
import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.ui.base.BaseNavigator;

import java.util.List;

public interface iTalukActivityContract {

    interface iTalukActivityNavigator extends BaseNavigator {
        boolean isSingleTalukShow();
        void loadTalukListFragment();
        void loadTalukDetailFragment();
    }

    interface iTalukActivityViewModel {
        void startLoadingData();
        List<Taluk> getTalukList(District district);
    }
}

package com.example.myapplication.ui.fragment.taluk.taluk_list;

import com.example.myapplication.data.model.api.response.haveri_data.District;
import com.example.myapplication.ui.base.BaseNavigator;

class iTalukListFragmentContract {

    interface iTalukListFragmentNavigator extends BaseNavigator {
    }

    public interface iTalukListFragmentViewModel {
        void startLoadingData(District district);
    }
}

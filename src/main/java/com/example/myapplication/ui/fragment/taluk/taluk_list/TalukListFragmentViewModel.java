package com.example.myapplication.ui.fragment.taluk.taluk_list;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.data.DataManager;
import com.example.myapplication.data.model.api.response.haveri_data.District;
import com.example.myapplication.ui.base.BaseViewModel;
import com.example.myapplication.utils.rx.SchedulerProvider;

public class TalukListFragmentViewModel extends BaseViewModel<iTalukListFragmentContract.iTalukListFragmentNavigator> implements
        iTalukListFragmentContract.iTalukListFragmentViewModel {

    private final MutableLiveData<District> district = new MutableLiveData<>();

    public TalukListFragmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void startLoadingData(District district) {
       this.district.setValue(district);
    }

    public MutableLiveData<District> getDistrict() {
        return district;
    }
}

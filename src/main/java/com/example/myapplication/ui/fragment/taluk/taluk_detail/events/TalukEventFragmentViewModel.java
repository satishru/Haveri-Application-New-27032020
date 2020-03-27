package com.example.myapplication.ui.fragment.taluk.taluk_detail.events;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.data.DataManager;
import com.example.myapplication.data.model.api.response.haveri_data.District;
import com.example.myapplication.data.model.api.response.haveri_data.Event;
import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.ui.base.BaseViewModel;
import com.example.myapplication.utils.CommonUtils;
import com.example.myapplication.utils.rx.SchedulerProvider;

import java.util.List;

public class TalukEventFragmentViewModel extends BaseViewModel<iTalukEventFragmentContract.iTalukEventFragmentNavigator>
        implements iTalukEventFragmentContract.iTalukEventFragmentViewModel {

    private final MutableLiveData<List<Event>> allTalukEventList = new MutableLiveData<>();

    public TalukEventFragmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void setEventData(Taluk selectedTaluk) {
        allTalukEventList.setValue(CommonUtils.getTalukEventList(selectedTaluk));
    }

    public MutableLiveData<List<Event>> getTalukEventList() {
        return allTalukEventList;
    }
}

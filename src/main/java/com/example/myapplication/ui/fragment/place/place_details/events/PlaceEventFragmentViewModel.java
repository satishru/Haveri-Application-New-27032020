package com.example.myapplication.ui.fragment.place.place_details.events;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.data.DataManager;
import com.example.myapplication.data.model.api.response.haveri_data.Event;
import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.ui.base.BaseViewModel;
import com.example.myapplication.utils.CommonUtils;
import com.example.myapplication.utils.rx.SchedulerProvider;

import java.util.List;

public class PlaceEventFragmentViewModel extends BaseViewModel<iPlaceEventFragmentContract.iPlaceEventFragmentNavigator>
        implements iPlaceEventFragmentContract.iPlaceEventFragmentViewModel {

    private final MutableLiveData<List<Event>> allPlaceEventList = new MutableLiveData<>();

    public PlaceEventFragmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void setEventData(Place selectedPlace) {
        allPlaceEventList.setValue(CommonUtils.getPlaceEventList(selectedPlace));
    }

    public MutableLiveData<List<Event>> getPlaceEventList() {
        return allPlaceEventList;
    }
}

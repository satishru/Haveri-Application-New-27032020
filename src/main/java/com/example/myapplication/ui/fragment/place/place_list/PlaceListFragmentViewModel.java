package com.example.myapplication.ui.fragment.place.place_list;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.data.DataManager;
import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.ui.base.BaseViewModel;
import com.example.myapplication.utils.rx.SchedulerProvider;

import java.util.List;

public class PlaceListFragmentViewModel extends BaseViewModel<iPlaceListFragmentContract.iPlaceListFragmentNavigator> implements
        iPlaceListFragmentContract.iPlaceListFragmentViewModel {

    private final MutableLiveData<List<Place>> placeList = new MutableLiveData<>();

    public PlaceListFragmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void startLoadingData(List<Place> placeList) {
        this.placeList.setValue(placeList);
    }

    public MutableLiveData<List<Place>> getPlaceList() {
        return placeList;
    }
}

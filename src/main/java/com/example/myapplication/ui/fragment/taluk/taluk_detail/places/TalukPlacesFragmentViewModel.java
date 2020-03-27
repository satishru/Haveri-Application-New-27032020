package com.example.myapplication.ui.fragment.taluk.taluk_detail.places;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.data.DataManager;
import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.ui.base.BaseViewModel;
import com.example.myapplication.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

public class TalukPlacesFragmentViewModel extends BaseViewModel<iTalukPlacesFragmentContract.iTalukPlacesFragmentNavigator>
        implements iTalukPlacesFragmentContract.iTalukPlacesFragmentViewModel {

    private final MutableLiveData<List<Place>> allPlaceList = new MutableLiveData<>();

    public TalukPlacesFragmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void setPlaceList(Taluk selectedTaluk) {
        List<Place> placeList = new ArrayList<>();
        if (selectedTaluk != null) {
            placeList.addAll(selectedTaluk.getPlaces());
        }
        allPlaceList.setValue(placeList);
    }

    public MutableLiveData<List<Place>> getPlaceList() {
        return allPlaceList;
    }
}

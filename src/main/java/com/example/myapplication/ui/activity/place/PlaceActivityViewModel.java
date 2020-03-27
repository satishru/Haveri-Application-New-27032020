package com.example.myapplication.ui.activity.place;

import com.example.myapplication.data.DataManager;
import com.example.myapplication.data.model.api.response.haveri_data.District;
import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.ui.base.BaseViewModel;
import com.example.myapplication.utils.AppConstants;
import com.example.myapplication.utils.CommonUtils;
import com.example.myapplication.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

public class PlaceActivityViewModel extends BaseViewModel<iPlaceActivityContract.iPlaceActivityNavigator>
        implements iPlaceActivityContract.iPlaceActivityViewModel {

    public PlaceActivityViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void startLoadingData() {
        if (getNavigator().isSinglePlaceShow()) {
            getNavigator().loadPlaceDetailFragment();
        } else {
            getNavigator().loadPlaceListFragment();
        }
    }

    @Override
    public List<Place> getPlaceList(District district, Taluk selectedTaluk) {
        List<Place> placeList = new ArrayList<>();
        if (district != null) {
            if(selectedTaluk != null) {
                placeList.addAll(selectedTaluk.getPlaces());
            } else {
                for (Taluk taluk : district.getTaluks()) {
                    placeList.addAll(taluk.getPlaces());
                }
            }
            placeList.addAll(
                    CommonUtils.mockList(new ArrayList<>(placeList), AppConstants.MOCK_LIST_SIZE));
        }
        return placeList;
    }
}

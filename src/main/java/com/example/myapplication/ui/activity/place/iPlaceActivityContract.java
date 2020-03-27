package com.example.myapplication.ui.activity.place;

import com.example.myapplication.data.model.api.response.haveri_data.District;
import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.ui.base.BaseNavigator;

import java.util.List;

public interface iPlaceActivityContract {
    interface iPlaceActivityNavigator extends BaseNavigator {
        boolean isSinglePlaceShow();

        void loadPlaceListFragment();

        void loadPlaceDetailFragment();
    }

    interface iPlaceActivityViewModel {
        void startLoadingData();

        List<Place> getPlaceList(District district, Taluk selectedTaluk);
    }
}

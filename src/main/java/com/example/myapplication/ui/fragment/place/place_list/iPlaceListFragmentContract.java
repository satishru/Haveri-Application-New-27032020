package com.example.myapplication.ui.fragment.place.place_list;

import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.ui.base.BaseNavigator;

import java.util.List;

public interface iPlaceListFragmentContract {
    interface iPlaceListFragmentNavigator extends BaseNavigator {
    }

    interface iPlaceListFragmentViewModel {
        void startLoadingData(List<Place> placeList);
    }
}

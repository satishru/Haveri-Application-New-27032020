package com.example.myapplication.ui.fragment.place.place_list;

import com.example.myapplication.data.model.api.response.haveri_data.District;
import com.example.myapplication.ui.base.BaseNavigator;

public interface iPlaceListFragmentContract {
    interface iPlaceListFragmentNavigator extends BaseNavigator {
    }

    interface iPlaceListFragmentViewModel {
        void startLoadingData(District district);
    }
}

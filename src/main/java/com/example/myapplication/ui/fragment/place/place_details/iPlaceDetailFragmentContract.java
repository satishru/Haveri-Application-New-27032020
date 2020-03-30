package com.example.myapplication.ui.fragment.place.place_details;

import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.ui.base.BaseNavigator;

public interface iPlaceDetailFragmentContract {
    interface iPlaceDetailFragmentNavigator extends BaseNavigator {
    }

    interface iPlaceDetailFragmentViewModel {
        void setImageUrl(Place place);
    }
}

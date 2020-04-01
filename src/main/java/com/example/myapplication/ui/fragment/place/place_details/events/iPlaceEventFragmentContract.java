package com.example.myapplication.ui.fragment.place.place_details.events;

import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.ui.base.BaseNavigator;

public interface iPlaceEventFragmentContract {
    interface iPlaceEventFragmentNavigator extends BaseNavigator {
    }

    interface iPlaceEventFragmentViewModel {
        void setEventData(Place selectedPlace);
    }
}

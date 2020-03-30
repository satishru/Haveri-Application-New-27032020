package com.example.myapplication.ui.fragment.place.place_details.about;

import com.example.myapplication.data.model.MapSingleObject;
import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.ui.base.BaseNavigator;

public interface iPlaceAboutFragmentContract {
    interface iPlaceAboutFragmentNavigator extends BaseNavigator {
        void setUpMap();

        void openMapSingleActivity(MapSingleObject mapSingleObject);

        void makePhoneCall(String phoneNumber);

        void openWebLink(String webLink);
    }

    interface iPlaceAboutFragmentViewModel {
        void onMapClick(Place selectedPlace);
    }
}

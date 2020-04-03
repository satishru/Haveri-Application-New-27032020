package com.example.myapplication.ui.fragment.place.place_details.video;

import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.ui.base.BaseNavigator;

public interface iPlaceVideosFragmentContract {
    interface iPlaceVideosFragmentNavigator extends BaseNavigator {

    }

    interface iPlaceVideosFragmentViewModel {
        void setVideoData(Place place);
    }
}

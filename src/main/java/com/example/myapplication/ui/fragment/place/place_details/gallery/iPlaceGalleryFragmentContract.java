package com.example.myapplication.ui.fragment.place.place_details.gallery;

import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.ui.base.BaseNavigator;

public interface iPlaceGalleryFragmentContract {
    interface iPlaceGalleryFragmentNavigator extends BaseNavigator {
    }

    interface iPlaceGalleryFragmentViewModel {
        void setImageData(Place place);
    }
}

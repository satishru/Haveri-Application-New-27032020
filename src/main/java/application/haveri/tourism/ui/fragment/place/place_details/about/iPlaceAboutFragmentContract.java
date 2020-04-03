package application.haveri.tourism.ui.fragment.place.place_details.about;

import application.haveri.tourism.data.model.MapSingleObject;
import application.haveri.tourism.data.model.api.response.haveri_data.Place;
import application.haveri.tourism.ui.base.BaseNavigator;

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

package application.haveri.tourism.ui.fragment.place.place_details.events;

import application.haveri.tourism.data.model.api.response.haveri_data.Place;
import application.haveri.tourism.ui.base.BaseNavigator;

public interface iPlaceEventFragmentContract {
    interface iPlaceEventFragmentNavigator extends BaseNavigator {
    }

    interface iPlaceEventFragmentViewModel {
        void setEventData(Place selectedPlace);
    }
}

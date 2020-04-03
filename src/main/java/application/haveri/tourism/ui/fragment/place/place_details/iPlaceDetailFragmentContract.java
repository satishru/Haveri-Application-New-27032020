package application.haveri.tourism.ui.fragment.place.place_details;

import application.haveri.tourism.data.model.api.response.haveri_data.Place;
import application.haveri.tourism.ui.base.BaseNavigator;

public interface iPlaceDetailFragmentContract {
    interface iPlaceDetailFragmentNavigator extends BaseNavigator {
    }

    interface iPlaceDetailFragmentViewModel {
        void setImageUrl(Place place);
    }
}

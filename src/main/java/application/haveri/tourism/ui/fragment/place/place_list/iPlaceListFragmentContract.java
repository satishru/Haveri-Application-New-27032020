package application.haveri.tourism.ui.fragment.place.place_list;

import application.haveri.tourism.data.model.api.response.haveri_data.Place;
import application.haveri.tourism.ui.base.BaseNavigator;

import java.util.List;

public interface iPlaceListFragmentContract {
    interface iPlaceListFragmentNavigator extends BaseNavigator {
    }

    interface iPlaceListFragmentViewModel {
        void startLoadingData(List<Place> placeList);
    }
}

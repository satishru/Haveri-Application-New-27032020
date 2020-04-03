package application.haveri.tourism.ui.activity.place;

import application.haveri.tourism.data.model.api.response.haveri_data.District;
import application.haveri.tourism.data.model.api.response.haveri_data.Place;
import application.haveri.tourism.data.model.api.response.haveri_data.Taluk;
import application.haveri.tourism.ui.base.BaseNavigator;

import java.util.List;

public interface iPlaceActivityContract {
    interface iPlaceActivityNavigator extends BaseNavigator {
        boolean isSinglePlaceShow();

        void loadPlaceListFragment();

        void loadPlaceDetailFragment();
    }

    interface iPlaceActivityViewModel {
        void startLoadingData();

        List<Place> getPlaceList(District district, Taluk selectedTaluk);
    }
}

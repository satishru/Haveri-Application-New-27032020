package application.haveri.tourism.ui.fragment.place.place_details.video;

import application.haveri.tourism.data.model.api.response.haveri_data.Place;
import application.haveri.tourism.ui.base.BaseNavigator;

public interface iPlaceVideosFragmentContract {
    interface iPlaceVideosFragmentNavigator extends BaseNavigator {

    }

    interface iPlaceVideosFragmentViewModel {
        void setVideoData(Place place);
    }
}

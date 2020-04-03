package application.haveri.tourism.ui.fragment.place.place_details.gallery;

import application.haveri.tourism.data.model.api.response.haveri_data.Place;
import application.haveri.tourism.ui.base.BaseNavigator;

public interface iPlaceGalleryFragmentContract {
    interface iPlaceGalleryFragmentNavigator extends BaseNavigator {
    }

    interface iPlaceGalleryFragmentViewModel {
        void setImageData(Place place);
    }
}

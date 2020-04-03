package application.haveri.tourism.ui.fragment.home;

import application.haveri.tourism.data.model.MapSingleObject;
import application.haveri.tourism.data.model.api.response.haveri_data.Images;
import application.haveri.tourism.data.model.api.response.haveri_data.Videos;
import application.haveri.tourism.ui.base.BaseNavigator;

import java.util.List;

class iHomeFragmentContract {

    interface iHomeFragmentNavigator extends BaseNavigator {
        void openOrCloseBottomSheet();
        void setUpMap();
        void openMapSingleActivity(MapSingleObject mapSingleObject);
        void openVideoSingleActivity(Videos video);
        void openExploreVideosActivity();
        void openTalukActivity();
        void openPlaceActivity();
        void setHomeButton(boolean visibility);
        void showBottomSheetSlideButton(boolean visibility);
        void openImageViewActivity(List<Images> imagesList, int selectedPosition);
    }

    interface iHomeFragmentViewModel {
        void startLoadingLocalData();
        void onTalukListViewAllClicked();
        void onPlaceListViewAllClicked();
        void onMapClick();
        void onVideoClick();
        void onExploreVideoClick();
        void handleBottomSheetState(int newState);
        void onImageListViewAllClicked();
    }
}

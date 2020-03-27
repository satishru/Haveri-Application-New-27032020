package com.example.myapplication.ui.fragment.home;

import com.example.myapplication.data.model.MapSingleObject;
import com.example.myapplication.data.model.api.response.haveri_data.Images;
import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.data.model.api.response.haveri_data.Videos;
import com.example.myapplication.ui.base.BaseNavigator;

import java.util.List;

class iHomeFragmentContract {

    interface iHomeFragmentNavigator extends BaseNavigator {
        void openOrCloseBottomSheet();
        void setUpMap();
        void openMapSingleActivity(MapSingleObject mapSingleObject);
        void openVideoSingleActivity(Videos video);
        void openExploreVideosActivity();
        void openTalukActivity();
        void setHomeButton(boolean visibility);
        void showBottomSheetSlideButton(boolean visibility);
        void openImageViewActivity(List<Images> imagesList, int selectedPosition);
    }

    interface iHomeFragmentViewModel {
        void startLoadingLocalData();
        void onTalukListViewAllClicked();
        void onMapClick();
        void onVideoClick();
        void onExploreVideoClick();
        void handleBottomSheetState(int newState);
        void onImageListViewAllClicked();
    }
}

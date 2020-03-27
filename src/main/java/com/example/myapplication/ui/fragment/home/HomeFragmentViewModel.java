package com.example.myapplication.ui.fragment.home;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.data.DataManager;
import com.example.myapplication.data.model.MapSingleObject;
import com.example.myapplication.data.model.api.response.BaseResponse;
import com.example.myapplication.data.model.api.response.haveri_data.District;
import com.example.myapplication.data.model.api.response.haveri_data.Event;
import com.example.myapplication.data.model.api.response.haveri_data.Images;
import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.data.model.api.response.haveri_data.Videos;
import com.example.myapplication.ui.base.BaseViewModel;
import com.example.myapplication.utils.CommonUtils;
import com.example.myapplication.utils.rx.SchedulerProvider;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeFragmentViewModel extends BaseViewModel<iHomeFragmentContract.iHomeFragmentNavigator> implements
        iHomeFragmentContract.iHomeFragmentViewModel {

    private final MutableLiveData<District> district = new MutableLiveData<>();
    private final MutableLiveData<List<Images>> allImageList = new MutableLiveData<>();
    private final MutableLiveData<String> description = new MutableLiveData<>();
    private final MutableLiveData<List<Event>> allEventList = new MutableLiveData<>();
    private final MutableLiveData<List<Videos>> allVideosList = new MutableLiveData<>();
    private final MutableLiveData<List<Place>> allPlaceList = new MutableLiveData<>();
    private final MutableLiveData<Videos> randomVideo = new MutableLiveData<>();

    public HomeFragmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void startLoadingLocalData() {
        loading(true);
        getCompositeDisposable().add(getDataManager()
                .getHaveriData()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(haveriDataList -> {
                    loading(false);
                    if (haveriDataList != null && haveriDataList.size() > 0) {
                        String json = CommonUtils.base64ToString(
                                haveriDataList.get(haveriDataList.size() - 1).jsonData);
                        setUpData(CommonUtils.convertJsonToObj(json, BaseResponse.class));
                    } /*else {
                        //openErrorDialog(R.drawable.ic_error,getNavigator().getStringFromId(R.string.error_db_load_data));
                    }*/
                }, throwable -> {
                    loading(false);
                    //openErrorDialog(R.drawable.ic_error,throwable.getLocalizedMessage());
                }));
    }

    @Override
    public void onTalukListViewAllClicked() {
        getNavigator().openTalukActivity();
    }

    @Override
    public void onMapClick() {
        MapSingleObject singleObject = new MapSingleObject();
        if (district.getValue() != null) {
            singleObject.setTitleEn(district.getValue().getDistrictNameEn());
            singleObject.setTitleKn(district.getValue().getDistrictNameKn());
            singleObject.setLatitude(district.getValue().getLatitude());
            singleObject.setLongitude(district.getValue().getLongitude());
            getNavigator().openMapSingleActivity(singleObject);
        }
    }

    @Override
    public void onVideoClick() {
        if (randomVideo.getValue() != null) {
            getNavigator().openVideoSingleActivity(randomVideo.getValue());
        }
    }

    @Override
    public void onExploreVideoClick() {
        if (allVideosList.getValue() != null) {
            getNavigator().openExploreVideosActivity();
        }
    }

    @Override
    public void handleBottomSheetState(int newState) {
        switch (newState) {
            case BottomSheetBehavior.STATE_HIDDEN:
            case BottomSheetBehavior.STATE_HALF_EXPANDED:
                getNavigator().setHomeButton(false);
                break;
            case BottomSheetBehavior.STATE_EXPANDED: {
                getNavigator().setHomeButton(true);
                getNavigator().showBottomSheetSlideButton(false);
            }
            break;
            case BottomSheetBehavior.STATE_COLLAPSED: {
                getNavigator().setHomeButton(false);
                getNavigator().showBottomSheetSlideButton(true);
            }
            break;
            case BottomSheetBehavior.STATE_DRAGGING:
            case BottomSheetBehavior.STATE_SETTLING:
                break;
        }
    }

    @Override
    public void onImageListViewAllClicked() {
        getNavigator().openImageViewActivity(allImageList.getValue(), 0);
    }

    private void setUpData(BaseResponse response) {
        if (response != null && response.getStates().size() > 0 && response.getStates().get(
                0).getDistricts().size() > 0) {
            district.setValue(response.getStates().get(0).getDistricts().get(0));
            if (district.getValue() != null) {
                description.setValue(getSelectedLanguage() == 0
                        ? district.getValue().getDescriptionEn() :
                        district.getValue().getDescriptionKn());

                setImageList(district.getValue().getTaluks());
                setVideosList(district.getValue().getTaluks());
                setEventList();
                setPlaceList(district.getValue().getTaluks());
            }
            getNavigator().setUpMap();
        }
    }

    private void setEventList() {
        allEventList.setValue(CommonUtils.getAllEventList());
    }

    private void setImageList(List<Taluk> talukList) {
        List<Images> imagesList = new ArrayList<>();
        for (Taluk taluk : talukList) {
            for (Place place : taluk.getPlaces()) {
                imagesList.addAll(place.getMediaGallery().getImagesData());
            }
        }
        allImageList.setValue(imagesList);
    }

    private void setVideosList(List<Taluk> talukList) {
        List<Videos> videosList = new ArrayList<>();
        for (Taluk taluk : talukList) {
            for (Place place : taluk.getPlaces()) {
                videosList.addAll(place.getMediaGallery().getVideosData());
            }
        }
        if (videosList.size() > 0) {
            randomVideo.setValue(getRandomVideoElement(videosList));
        }
        allVideosList.setValue(videosList);
    }

    private void setPlaceList(List<Taluk> talukList) {
        List<Place> placeList = new ArrayList<>();
        for (Taluk taluk : talukList) {
            placeList.addAll(taluk.getPlaces());
        }
        allPlaceList.setValue(placeList);
    }

    private Videos getRandomVideoElement(List<Videos> videosList) {
        if (videosList.size() > 0) {
            Random rand = new Random();
            int randomPosition = rand.nextInt(videosList.size());
            if (videosList.size() > randomPosition) {
                return videosList.get(randomPosition);
            }
            return videosList.get(0);
        }
        return null;
    }

    public MutableLiveData<District> getDistrict() {
        return district;
    }

    public MutableLiveData<List<Images>> getImageList() {
        return allImageList;
    }

    public MutableLiveData<List<Videos>> getVideosList() {
        return allVideosList;
    }

    public MutableLiveData<List<Event>> getEventList() {
        return allEventList;
    }

    public MutableLiveData<String> getDescription() {
        return description;
    }

    public MutableLiveData<Videos> getRandomVideo() {
        return randomVideo;
    }

    public MutableLiveData<List<Place>> getPlaceList() {
        return allPlaceList;
    }
}

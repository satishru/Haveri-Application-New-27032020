package com.example.myapplication.ui.fragment.place.place_details.video;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.data.DataManager;
import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.data.model.api.response.haveri_data.Videos;
import com.example.myapplication.ui.base.BaseViewModel;
import com.example.myapplication.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

public class PlaceVideosFragmentViewModel extends BaseViewModel<iPlaceVideosFragmentContract.iPlaceVideosFragmentNavigator>
        implements iPlaceVideosFragmentContract.iPlaceVideosFragmentViewModel {

    private final MutableLiveData<List<Videos>> allVideosList = new MutableLiveData<>();

    public PlaceVideosFragmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void setVideoData(Place place) {
        if (place != null && place.getMediaGallery() != null) {
            List<Videos> videosList = new ArrayList<>(place.getMediaGallery().getVideosData());
            allVideosList.setValue(videosList);
        }
    }

    public MutableLiveData<List<Videos>> getVideosList() {
        return allVideosList;
    }
}

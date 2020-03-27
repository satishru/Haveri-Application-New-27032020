package com.example.myapplication.ui.fragment.videos.video_list;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.data.DataManager;
import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.data.model.api.response.haveri_data.Videos;
import com.example.myapplication.ui.base.BaseViewModel;
import com.example.myapplication.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

public class ExploreVideoFragmentViewModel extends BaseViewModel<iExploreVideoFragmentContract.iExploreVideoFragmentNavigator>
        implements iExploreVideoFragmentContract.iExploreVideoFragmentViewModel {

    private final MutableLiveData<List<Videos>> allVideosList = new MutableLiveData<>();

    public ExploreVideoFragmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void setVideosList(List<Taluk> talukList) {
        List<Videos> videosList = new ArrayList<>();
        for (Taluk taluk : talukList) {
            for (Place place : taluk.getPlaces()) {
                videosList.addAll(place.getMediaGallery().getVideosData());
            }
        }
        allVideosList.setValue(videosList);
    }

    public MutableLiveData<List<Videos>> getVideosList() {
        return allVideosList;
    }

}

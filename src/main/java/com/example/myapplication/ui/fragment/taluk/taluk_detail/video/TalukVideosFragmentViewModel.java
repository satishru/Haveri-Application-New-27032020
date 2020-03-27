package com.example.myapplication.ui.fragment.taluk.taluk_detail.video;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.data.DataManager;
import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.data.model.api.response.haveri_data.Videos;
import com.example.myapplication.ui.base.BaseViewModel;
import com.example.myapplication.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

public class TalukVideosFragmentViewModel extends BaseViewModel<iTalukVideosFragmentContract.iTalukVideosFragmentNavigator>
        implements iTalukVideosFragmentContract.iTalukVideosFragmentViewModel {

    private final MutableLiveData<List<Videos>> allVideosList = new MutableLiveData<>();

    public TalukVideosFragmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void setVideoData(Taluk taluk) {
        if (taluk != null) {
            List<Videos> videosList = new ArrayList<>();
            for (Place place : taluk.getPlaces()) {
                videosList.addAll(place.getMediaGallery().getVideosData());
            }
            allVideosList.setValue(videosList);
        }
    }

    public MutableLiveData<List<Videos>> getVideosList() {
        return allVideosList;
    }
}

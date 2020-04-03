package application.haveri.tourism.ui.fragment.taluk.taluk_detail.video;

import androidx.lifecycle.MutableLiveData;

import application.haveri.tourism.data.DataManager;
import application.haveri.tourism.data.model.api.response.haveri_data.Place;
import application.haveri.tourism.data.model.api.response.haveri_data.Taluk;
import application.haveri.tourism.data.model.api.response.haveri_data.Videos;
import application.haveri.tourism.ui.base.BaseViewModel;
import application.haveri.tourism.utils.rx.SchedulerProvider;

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

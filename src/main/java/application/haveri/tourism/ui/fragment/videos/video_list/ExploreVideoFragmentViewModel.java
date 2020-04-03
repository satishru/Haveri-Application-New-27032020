package application.haveri.tourism.ui.fragment.videos.video_list;

import androidx.lifecycle.MutableLiveData;

import application.haveri.tourism.data.DataManager;
import application.haveri.tourism.data.model.api.response.haveri_data.Place;
import application.haveri.tourism.data.model.api.response.haveri_data.Taluk;
import application.haveri.tourism.data.model.api.response.haveri_data.Videos;
import application.haveri.tourism.ui.base.BaseViewModel;
import application.haveri.tourism.utils.rx.SchedulerProvider;

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

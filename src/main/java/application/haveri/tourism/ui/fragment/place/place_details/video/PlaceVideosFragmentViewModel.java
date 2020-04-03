package application.haveri.tourism.ui.fragment.place.place_details.video;

import androidx.lifecycle.MutableLiveData;

import application.haveri.tourism.data.DataManager;
import application.haveri.tourism.data.model.api.response.haveri_data.Place;
import application.haveri.tourism.data.model.api.response.haveri_data.Videos;
import application.haveri.tourism.ui.base.BaseViewModel;
import application.haveri.tourism.utils.rx.SchedulerProvider;

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

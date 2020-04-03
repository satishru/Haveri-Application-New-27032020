package application.haveri.tourism.ui.fragment.place.place_details.gallery;

import androidx.lifecycle.MutableLiveData;

import application.haveri.tourism.data.DataManager;
import application.haveri.tourism.data.model.api.response.haveri_data.Images;
import application.haveri.tourism.data.model.api.response.haveri_data.Place;
import application.haveri.tourism.ui.base.BaseViewModel;
import application.haveri.tourism.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

public class PlaceGalleryFragmentViewModel extends BaseViewModel<iPlaceGalleryFragmentContract.iPlaceGalleryFragmentNavigator>
        implements iPlaceGalleryFragmentContract.iPlaceGalleryFragmentViewModel {

    private final MutableLiveData<List<Images>> allImageList = new MutableLiveData<>();

    public PlaceGalleryFragmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void setImageData(Place place) {
        List<Images> imagesList = new ArrayList<>();
        if (place != null && place.getMediaGallery() != null) {
            imagesList.addAll(place.getMediaGallery().getImagesData());
        }
        allImageList.setValue(imagesList);
    }

    public MutableLiveData<List<Images>> getImageList() {
        return allImageList;
    }
}

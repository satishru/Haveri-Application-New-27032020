package com.example.myapplication.ui.fragment.place.place_details.gallery;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.data.DataManager;
import com.example.myapplication.data.model.api.response.haveri_data.Images;
import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.ui.base.BaseViewModel;
import com.example.myapplication.utils.rx.SchedulerProvider;

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

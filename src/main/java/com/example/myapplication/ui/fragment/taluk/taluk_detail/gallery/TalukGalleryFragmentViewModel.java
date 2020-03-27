package com.example.myapplication.ui.fragment.taluk.taluk_detail.gallery;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.data.DataManager;
import com.example.myapplication.data.model.api.response.haveri_data.Images;
import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.ui.base.BaseViewModel;
import com.example.myapplication.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

public class TalukGalleryFragmentViewModel extends BaseViewModel<iTalukGalleryFragmentContract.iTalukGalleryFragmentNavigator> implements
        iTalukGalleryFragmentContract.iTalukGalleryFragmentViewModel {

    private final MutableLiveData<List<Images>> allImageList = new MutableLiveData<>();

    public TalukGalleryFragmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void setImageData(Taluk taluk) {
        if (taluk != null) {
            List<Images> imagesList = new ArrayList<>();
            for (Place place : taluk.getPlaces()) {
                imagesList.addAll(place.getMediaGallery().getImagesData());
            }
            allImageList.setValue(imagesList);
        }
    }

    public MutableLiveData<List<Images>> getImageList() {
        return allImageList;
    }
}

package com.example.myapplication.ui.fragment.place.place_details;

import androidx.databinding.ObservableField;

import com.example.myapplication.data.DataManager;
import com.example.myapplication.data.model.api.response.haveri_data.Images;
import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.ui.base.BaseViewModel;
import com.example.myapplication.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlaceDetailFragmentViewModel extends BaseViewModel<iPlaceDetailFragmentContract.iPlaceDetailFragmentNavigator>
        implements iPlaceDetailFragmentContract.iPlaceDetailFragmentViewModel {

    public ObservableField<String> imageUrl = new ObservableField<>("");

    public PlaceDetailFragmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void setImageUrl(Place place) {
        if(place != null) {
            List<Images> imagesList = new ArrayList<>();
            if (place.getMediaGallery() != null) {
                imagesList.addAll(place.getMediaGallery().getImagesData());
            }
            if(imagesList.size() > 0) {
                imageUrl.set(getRandomElement(imagesList));
            }
        }
    }

    private String getRandomElement(List<Images> list) {
        if(list.size() > 0) {
            Random rand = new Random();
            int randomPosition = rand.nextInt(list.size());
            if (list.size() > randomPosition) {
                return list.get(randomPosition).getImageUrl();
            }
            return list.get(0).getImageUrl();
        }
        return "";
    }
}

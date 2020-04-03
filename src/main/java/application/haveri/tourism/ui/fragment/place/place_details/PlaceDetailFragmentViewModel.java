package application.haveri.tourism.ui.fragment.place.place_details;

import androidx.databinding.ObservableField;

import application.haveri.tourism.data.DataManager;
import application.haveri.tourism.data.model.api.response.haveri_data.Images;
import application.haveri.tourism.data.model.api.response.haveri_data.Place;
import application.haveri.tourism.ui.base.BaseViewModel;
import application.haveri.tourism.utils.rx.SchedulerProvider;

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

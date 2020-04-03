package application.haveri.tourism.ui.fragment.taluk.taluk_detail;

import androidx.databinding.ObservableField;

import application.haveri.tourism.data.DataManager;
import application.haveri.tourism.data.model.api.response.haveri_data.Images;
import application.haveri.tourism.data.model.api.response.haveri_data.Place;
import application.haveri.tourism.data.model.api.response.haveri_data.Taluk;
import application.haveri.tourism.ui.base.BaseViewModel;
import application.haveri.tourism.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TalukDetailFragmentViewModel extends BaseViewModel<iTalukDetailFragmentContract.iTalukDetailFragmentNavigator> implements
        iTalukDetailFragmentContract.iTalukDetailFragmentViewModel {

    public ObservableField<String> imageUrl = new ObservableField<>("");

    public TalukDetailFragmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void setImageUrl(Taluk taluk) {
        if(taluk != null) {
            List<Images> imagesList = new ArrayList<>();
            for (Place place : taluk.getPlaces()) {
                imagesList.addAll(place.getMediaGallery().getImagesData());
            }
            if(imagesList.size() > 0) {
                imageUrl.set(getRandomElement(imagesList));
            }
        }
    }

    private String getRandomElement(List<Images>  list) {
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

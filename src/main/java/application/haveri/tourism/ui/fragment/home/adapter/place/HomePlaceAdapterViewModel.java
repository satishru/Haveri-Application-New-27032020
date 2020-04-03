package application.haveri.tourism.ui.fragment.home.adapter.place;

import androidx.databinding.ObservableField;

import application.haveri.tourism.data.model.api.response.haveri_data.Place;
import application.haveri.tourism.data.model.api.response.haveri_data.Taluk;
import application.haveri.tourism.utils.CommonUtils;
import application.haveri.tourism.utils.Language;

public class HomePlaceAdapterViewModel {

    public ObservableField<String> placeName;
    public ObservableField<String> talukName;
    public ObservableField<String> placeDescription;
    public ObservableField<String> imageUrl;

    public int language;
    public Place place;
    private HomePlaceAdapterViewModelListener mListener;

    HomePlaceAdapterViewModel(Place place, int language, HomePlaceAdapterViewModelListener listener) {
        this.place = place;
        this.mListener = listener;
        this.language = language;
        placeName = new ObservableField<>(
                language == Language.EN.getValue() ? place.getPlaceNameEn() :
                        place.getPlaceNameKn());
        placeDescription = new ObservableField<>(
                language == Language.EN.getValue() ? place.getDescriptionEn() :
                        place.getDescriptionKn());
        imageUrl = new ObservableField<>(getImage());
        Taluk taluk = CommonUtils.getTalukUsingPlace(place);
        if(taluk != null) {
            talukName = new ObservableField<>(
                    language == Language.EN.getValue() ? taluk.getTalukNameEn() :
                            taluk.getTalukNameKn());
        }
    }

    private String getImage() {
        if (place.getMediaGallery().getImagesData().size() > 0) {
            return place.getMediaGallery().getImagesData().get(0).getImageUrl();
        }
        return "";
    }

    public void onItemClick() {
        if (mListener != null) {
            mListener.onItemClick(place);
        }
    }

    public interface HomePlaceAdapterViewModelListener {
        void onItemClick(Place place);
    }
}

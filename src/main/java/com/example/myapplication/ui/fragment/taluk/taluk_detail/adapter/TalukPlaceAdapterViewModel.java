package com.example.myapplication.ui.fragment.taluk.taluk_detail.adapter;

import androidx.databinding.ObservableField;

import com.example.myapplication.HaveriApplication;
import com.example.myapplication.R;
import com.example.myapplication.data.model.api.response.haveri_data.Place;
import com.example.myapplication.utils.CommonUtils;
import com.example.myapplication.utils.Language;

public class TalukPlaceAdapterViewModel {
    public ObservableField<String> placeName;
    public ObservableField<String> placeDescription;
    public ObservableField<String> imageUrl;
    public ObservableField<String> distanceInKm;
    public ObservableField<String> eventCount;

    public int language;
    public Place place;
    private TalukPlaceAdapterViewModelListener mListener;

    TalukPlaceAdapterViewModel(Place place, int language, TalukPlaceAdapterViewModelListener listener) {
        this.place = place;
        this.mListener = listener;
        this.language = language;
        placeName = new ObservableField<>(
                language == Language.EN.getValue() ? place.getPlaceNameEn() :
                        place.getPlaceNameKn());
        placeDescription = new ObservableField<>(
                CommonUtils.toHtml(language == Language.EN.getValue() ? place.getDescriptionEn() :
                        place.getDescriptionKn()));
        imageUrl = new ObservableField<>(getImage());
        distanceInKm = new ObservableField<>(
                CommonUtils.getDistance(place.getLatitude(), place.getLongitude(),
                        HaveriApplication.getInstance().getLocation()));
        eventCount = new ObservableField<>(getEventCountString());
    }

    private String getEventCountString() {
        String eventCountStr = "";
        int placeEventCount = CommonUtils.getPlaceEventCount(place);
        if (placeEventCount > 0) {
            String eventCountSrc = (placeEventCount > 1) ?
                    HaveriApplication.getInstance().getResources().getString(
                            R.string.label_count_events) :
                    HaveriApplication.getInstance().getResources().getString(
                            R.string.label_count_event);
            eventCountStr = String.format(eventCountSrc, placeEventCount);
        }
        return eventCountStr;
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

    public interface TalukPlaceAdapterViewModelListener {
        void onItemClick(Place place);
    }
}

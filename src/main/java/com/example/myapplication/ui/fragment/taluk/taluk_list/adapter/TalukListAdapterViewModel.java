package com.example.myapplication.ui.fragment.taluk.taluk_list.adapter;

import androidx.databinding.ObservableField;

import com.example.myapplication.HaveriApplication;
import com.example.myapplication.R;
import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.utils.CommonUtils;

public class TalukListAdapterViewModel {

    public ObservableField<String> talukName;
    public ObservableField<String> talukDescription;
    public ObservableField<String> imageUrl;
    public ObservableField<String> distanceInKm;
    public ObservableField<String> placeCount;
    public ObservableField<String> eventCount;

    private Taluk taluk;
    private TalukListAdapterViewModelListener mListener;

    TalukListAdapterViewModel(Taluk taluk, int language, TalukListAdapterViewModelListener listener) {
        this.taluk = taluk;
        this.mListener = listener;
        talukName = new ObservableField<>(
                language == 0 ? taluk.getTalukNameEn() : taluk.getTalukNameKn());
        talukDescription = new ObservableField<>(
                language == 0 ? taluk.getDescriptionEn() : taluk.getDescriptionKn());
        imageUrl = new ObservableField<>(getImage());
        distanceInKm = new ObservableField<>(
                CommonUtils.getDistance(taluk.getLatitude(), taluk.getLongitude(),
                        HaveriApplication.getInstance().getLocation()));
        placeCount = new ObservableField<>(getPlaceCountString());
        eventCount = new ObservableField<>(getEventCountString());
    }

    private String getPlaceCountString() {
        String placeCountStr = "";
        int talukPlaceCount = taluk.getPlaces().size();
        if (talukPlaceCount > 0) {
            String placeCountSrc = (talukPlaceCount > 1) ?
                    HaveriApplication.getInstance().getResources().getString(
                            R.string.label_count_places) :
                    HaveriApplication.getInstance().getResources().getString(
                            R.string.label_count_place);
            placeCountStr = String.format(placeCountSrc, taluk.getPlaces().size());
        }
        return placeCountStr;
    }

    private String getEventCountString() {
        String eventCountStr = "";
        int talukEventCount = CommonUtils.getTalukEventCount(taluk);
        if (talukEventCount > 0) {
            String eventCountSrc = (talukEventCount > 1) ?
                    HaveriApplication.getInstance().getResources().getString(
                            R.string.label_count_events) :
                    HaveriApplication.getInstance().getResources().getString(
                            R.string.label_count_event);
            eventCountStr = String.format(eventCountSrc, talukEventCount);
        }
        return eventCountStr;
    }

    private String getImage() {
        if (taluk.getPlaces().size() > 0 && taluk.getPlaces().get(
                0).getMediaGallery().getImagesData().size() > 0) {
            return taluk.getPlaces().get(0).getMediaGallery().getImagesData().get(0).getImageUrl();
        }
        return "";
    }

    public void onItemClick() {
        if (mListener != null) {
            mListener.onItemClick(taluk);
        }
    }

    public interface TalukListAdapterViewModelListener {
        void onItemClick(Taluk taluk);
    }
}

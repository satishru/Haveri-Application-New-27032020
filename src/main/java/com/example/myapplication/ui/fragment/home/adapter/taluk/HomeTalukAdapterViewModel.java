package com.example.myapplication.ui.fragment.home.adapter.taluk;

import androidx.databinding.ObservableField;

import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.utils.Language;

public class HomeTalukAdapterViewModel {

    public ObservableField<String> talukName;
    public ObservableField<String> talukDescription;
    public ObservableField<String> imageUrl;

    public int language;
    public Taluk taluk;
    private HomeTalukAdapterViewModelListener mListener;

    HomeTalukAdapterViewModel(Taluk taluk, int language, HomeTalukAdapterViewModelListener listener) {
        this.taluk = taluk;
        this.mListener = listener;
        this.language = language;
        talukName = new ObservableField<>(language == Language.EN.getValue() ? taluk.getTalukNameEn() : taluk.getTalukNameKn());
        talukDescription = new ObservableField<>( language == Language.EN.getValue() ? taluk.getDescriptionEn() : taluk.getDescriptionKn());
        imageUrl = new ObservableField<>(getImage());
    }

    private String getImage() {
        if(taluk.getPlaces().size() > 0 && taluk.getPlaces().get(0).getMediaGallery().getImagesData().size()>0) {
            return taluk.getPlaces().get(0).getMediaGallery().getImagesData().get(0).getImageUrl();
        }
        return "";
    }

    public void onItemClick() {
        if(mListener != null) {
            mListener.onItemClick(taluk);
        }
    }

    public interface HomeTalukAdapterViewModelListener {
        void onItemClick(Taluk taluk);
    }
}

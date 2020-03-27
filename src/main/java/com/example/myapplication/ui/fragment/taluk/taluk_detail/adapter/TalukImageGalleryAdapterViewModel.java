package com.example.myapplication.ui.fragment.taluk.taluk_detail.adapter;

import androidx.databinding.ObservableField;

import com.example.myapplication.data.model.api.response.haveri_data.Images;
import com.example.myapplication.utils.Language;

public class TalukImageGalleryAdapterViewModel {
    public ObservableField<String> imageUrl;
    public ObservableField<String> imageTitle;

    private int position;
    private Images image;
    private TalukImageGalleryAdapterViewModelListener mListener;

    TalukImageGalleryAdapterViewModel(int position, Images image, TalukImageGalleryAdapterViewModelListener mListener, int language) {
        this.position  = position;
        this.image     = image;
        this.mListener = mListener;
        imageUrl = new ObservableField<>(image.getImageUrl());
        imageTitle = new ObservableField<>(Language.EN.getValue() == language ? image.getImageTitleEn() : image.getImageTitleKn());
    }

    public void onItemClick() {
        if(mListener != null) {
            mListener.onItemClick(position);
        }
    }

    public interface TalukImageGalleryAdapterViewModelListener {
        void onItemClick(int position);
    }
}

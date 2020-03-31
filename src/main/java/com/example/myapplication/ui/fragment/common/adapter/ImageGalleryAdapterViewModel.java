package com.example.myapplication.ui.fragment.common.adapter;

import androidx.databinding.ObservableField;

import com.example.myapplication.data.model.api.response.haveri_data.Images;
import com.example.myapplication.utils.Language;

public class ImageGalleryAdapterViewModel {
    public ObservableField<String> imageUrl;
    public ObservableField<String> imageTitle;

    private int position;
    private ImageGalleryAdapterViewModelListener mListener;

    ImageGalleryAdapterViewModel(int position, Images image, ImageGalleryAdapterViewModelListener mListener, int language) {
        this.position  = position;
        this.mListener = mListener;
        imageUrl = new ObservableField<>(image.getImageUrl());
        imageTitle = new ObservableField<>(Language.EN.getValue() == language ? image.getImageTitleEn() : image.getImageTitleKn());
    }

    public void onItemClick() {
        if(mListener != null) {
            mListener.onItemClick(position);
        }
    }

    public interface ImageGalleryAdapterViewModelListener {
        void onItemClick(int position);
    }
}

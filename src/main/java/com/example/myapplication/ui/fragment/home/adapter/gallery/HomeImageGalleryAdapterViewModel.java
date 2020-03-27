package com.example.myapplication.ui.fragment.home.adapter.gallery;

import androidx.databinding.ObservableField;

import com.example.myapplication.data.model.api.response.haveri_data.Images;
import com.example.myapplication.utils.Language;

public class HomeImageGalleryAdapterViewModel {

    public ObservableField<String> imageTitle;
    public ObservableField<String> imageUrl;

    private int position;
    private HomeImageGalleryAdapterViewModelListener mListener;

    HomeImageGalleryAdapterViewModel(Images image, int position, int language, HomeImageGalleryAdapterViewModelListener mListener) {
        this.mListener = mListener;
        this.position = position;
        imageTitle = new ObservableField<>(
                language == Language.EN.getValue() ? image.getImageTitleEn() :
                        image.getImageTitleKn());
        imageUrl = new ObservableField<>(image.getImageUrl());
    }

    public void onItemClick() {
        if (mListener != null) {
            mListener.onItemClick(position);
        }
    }

    String getRemainingCount(int totalSize, int currentSize) {
        if (totalSize == currentSize) {
            return "";
        } else {
            return "+" + (totalSize - currentSize);
        }
    }

    public interface HomeImageGalleryAdapterViewModelListener {
        void onItemClick(int position);
    }
}

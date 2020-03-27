package com.example.myapplication.ui.fragment.taluk.taluk_detail.gallery;

import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.ui.base.BaseNavigator;

class iTalukGalleryFragmentContract {
    interface iTalukGalleryFragmentNavigator extends BaseNavigator {
    }

    public interface iTalukGalleryFragmentViewModel {
        void setImageData(Taluk taluk);
    }
}

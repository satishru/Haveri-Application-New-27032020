package com.example.myapplication.ui.fragment.taluk.taluk_detail.video;

import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.ui.base.BaseNavigator;

public interface iTalukVideosFragmentContract {
    interface iTalukVideosFragmentNavigator extends BaseNavigator {
    }

    interface iTalukVideosFragmentViewModel {
        void setVideoData(Taluk taluk);
    }
}

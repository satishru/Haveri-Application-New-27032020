package com.example.myapplication.ui.fragment.taluk.taluk_detail;

import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.ui.base.BaseNavigator;

public interface iTalukDetailFragmentContract {
    interface iTalukDetailFragmentNavigator extends BaseNavigator {
    }

    interface iTalukDetailFragmentViewModel {
        void setImageUrl(Taluk taluk);
    }
}

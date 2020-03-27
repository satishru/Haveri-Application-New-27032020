package com.example.myapplication.ui.fragment.taluk.taluk_detail.events;

import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.ui.base.BaseNavigator;

public interface iTalukEventFragmentContract {
    interface iTalukEventFragmentNavigator extends BaseNavigator {
    }

    interface iTalukEventFragmentViewModel {
        void setEventData(Taluk selectedTaluk);
    }
}

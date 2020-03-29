package com.example.myapplication.ui.fragment.taluk.taluk_detail.about;

import com.example.myapplication.data.model.MapSingleObject;
import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.ui.base.BaseNavigator;

class iTalukAboutFragmentContract {
    interface iTalukAboutFragmentNavigator extends BaseNavigator {
        void setUpMap();
        void openMapSingleActivity(MapSingleObject mapSingleObject);
    }

    interface iTalukAboutFragmentViewModel {
        void onMapClick(Taluk selectedTaluk);
    }
}

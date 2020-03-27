package com.example.myapplication.ui.fragment.taluk.taluk_detail.places;

import com.example.myapplication.data.model.api.response.haveri_data.Taluk;
import com.example.myapplication.ui.base.BaseNavigator;

public interface iTalukPlacesFragmentContract {
    interface iTalukPlacesFragmentNavigator extends BaseNavigator {

    }

    interface iTalukPlacesFragmentViewModel{
        void setPlaceList(Taluk selectedTaluk);
    }
}

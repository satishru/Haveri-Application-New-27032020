package com.example.myapplication.ui.activity.event;

import com.example.myapplication.data.model.api.response.haveri_data.District;
import com.example.myapplication.data.model.api.response.haveri_data.Event;
import com.example.myapplication.ui.base.BaseNavigator;

public interface iEventDetailActivityContract {
    interface iEventDetailActivityNavigator extends BaseNavigator {
        void playVideo();
        void navigateToMap();
    }

    interface iEventDetailActivityViewModel {
        void setEventData(Event event, District district);
        void onButtonPlayClick();
        void onNavigateImageClick();
    }
}

package application.haveri.tourism.ui.activity.event;

import application.haveri.tourism.data.model.api.response.haveri_data.District;
import application.haveri.tourism.data.model.api.response.haveri_data.Event;
import application.haveri.tourism.ui.base.BaseNavigator;

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

package application.haveri.tourism.ui.fragment.taluk.taluk_detail.about;

import application.haveri.tourism.data.model.MapSingleObject;
import application.haveri.tourism.data.model.api.response.haveri_data.Taluk;
import application.haveri.tourism.ui.base.BaseNavigator;

class iTalukAboutFragmentContract {
    interface iTalukAboutFragmentNavigator extends BaseNavigator {
        void setUpMap();
        void openMapSingleActivity(MapSingleObject mapSingleObject);
    }

    interface iTalukAboutFragmentViewModel {
        void onMapClick(Taluk selectedTaluk);
    }
}

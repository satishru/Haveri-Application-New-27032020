package application.haveri.tourism.ui.fragment.taluk.taluk_detail.places;

import application.haveri.tourism.data.model.api.response.haveri_data.Taluk;
import application.haveri.tourism.ui.base.BaseNavigator;

public interface iTalukPlacesFragmentContract {
    interface iTalukPlacesFragmentNavigator extends BaseNavigator {

    }

    interface iTalukPlacesFragmentViewModel{
        void setPlaceList(Taluk selectedTaluk);
    }
}

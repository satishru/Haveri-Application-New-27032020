package application.haveri.tourism.ui.fragment.taluk.taluk_detail.events;

import application.haveri.tourism.data.model.api.response.haveri_data.Taluk;
import application.haveri.tourism.ui.base.BaseNavigator;

public interface iTalukEventFragmentContract {
    interface iTalukEventFragmentNavigator extends BaseNavigator {
    }

    interface iTalukEventFragmentViewModel {
        void setEventData(Taluk selectedTaluk);
    }
}

package application.haveri.tourism.ui.fragment.taluk.taluk_detail;

import application.haveri.tourism.data.model.api.response.haveri_data.Taluk;
import application.haveri.tourism.ui.base.BaseNavigator;

public interface iTalukDetailFragmentContract {
    interface iTalukDetailFragmentNavigator extends BaseNavigator {
    }

    interface iTalukDetailFragmentViewModel {
        void setImageUrl(Taluk taluk);
    }
}

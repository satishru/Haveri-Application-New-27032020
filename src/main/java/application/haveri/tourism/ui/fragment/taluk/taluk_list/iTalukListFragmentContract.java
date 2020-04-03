package application.haveri.tourism.ui.fragment.taluk.taluk_list;

import application.haveri.tourism.data.model.api.response.haveri_data.District;
import application.haveri.tourism.ui.base.BaseNavigator;

class iTalukListFragmentContract {

    interface iTalukListFragmentNavigator extends BaseNavigator {
    }

    public interface iTalukListFragmentViewModel {
        void startLoadingData(District district);
    }
}

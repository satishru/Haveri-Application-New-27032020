package application.haveri.tourism.ui.activity.taluk;

import application.haveri.tourism.data.model.api.response.haveri_data.District;
import application.haveri.tourism.data.model.api.response.haveri_data.Taluk;
import application.haveri.tourism.ui.base.BaseNavigator;

import java.util.List;

public interface iTalukActivityContract {

    interface iTalukActivityNavigator extends BaseNavigator {
        boolean isSingleTalukShow();
        void loadTalukListFragment();
        void loadTalukDetailFragment();
    }

    interface iTalukActivityViewModel {
        void startLoadingData();
        List<Taluk> getTalukList(District district);
    }
}

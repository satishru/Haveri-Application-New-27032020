package application.haveri.tourism.ui.activity.taluk;

import application.haveri.tourism.data.DataManager;
import application.haveri.tourism.data.model.api.response.haveri_data.District;
import application.haveri.tourism.data.model.api.response.haveri_data.Taluk;
import application.haveri.tourism.ui.base.BaseViewModel;
import application.haveri.tourism.utils.AppConstants;
import application.haveri.tourism.utils.CommonUtils;
import application.haveri.tourism.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

public class TalukActivityViewModel extends BaseViewModel<iTalukActivityContract.iTalukActivityNavigator> implements
        iTalukActivityContract.iTalukActivityViewModel {

    public TalukActivityViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void startLoadingData() {
        if (getNavigator().isSingleTalukShow()) {
            getNavigator().loadTalukDetailFragment();
        } else {
            getNavigator().loadTalukListFragment();
        }
    }

    @Override
    public List<Taluk> getTalukList(District district) {
        List<Taluk> talukList = new ArrayList<>();
        if (district != null) {
            talukList.addAll(
                    CommonUtils.mockList(new ArrayList<>(district.getTaluks()), AppConstants.MOCK_LIST_SIZE));
        }
        return talukList;
    }
}

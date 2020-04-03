package application.haveri.tourism.ui.fragment.taluk.taluk_list;

import androidx.lifecycle.MutableLiveData;

import application.haveri.tourism.data.DataManager;
import application.haveri.tourism.data.model.api.response.haveri_data.District;
import application.haveri.tourism.ui.base.BaseViewModel;
import application.haveri.tourism.utils.rx.SchedulerProvider;

public class TalukListFragmentViewModel extends BaseViewModel<iTalukListFragmentContract.iTalukListFragmentNavigator> implements
        iTalukListFragmentContract.iTalukListFragmentViewModel {

    private final MutableLiveData<District> district = new MutableLiveData<>();

    public TalukListFragmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void startLoadingData(District district) {
       this.district.setValue(district);
    }

    public MutableLiveData<District> getDistrict() {
        return district;
    }
}

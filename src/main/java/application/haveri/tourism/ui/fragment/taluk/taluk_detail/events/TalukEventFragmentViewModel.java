package application.haveri.tourism.ui.fragment.taluk.taluk_detail.events;

import androidx.lifecycle.MutableLiveData;

import application.haveri.tourism.data.DataManager;
import application.haveri.tourism.data.model.api.response.haveri_data.Event;
import application.haveri.tourism.data.model.api.response.haveri_data.Taluk;
import application.haveri.tourism.ui.base.BaseViewModel;
import application.haveri.tourism.utils.CommonUtils;
import application.haveri.tourism.utils.rx.SchedulerProvider;

import java.util.List;

public class TalukEventFragmentViewModel extends BaseViewModel<iTalukEventFragmentContract.iTalukEventFragmentNavigator>
        implements iTalukEventFragmentContract.iTalukEventFragmentViewModel {

    private final MutableLiveData<List<Event>> allTalukEventList = new MutableLiveData<>();

    public TalukEventFragmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void setEventData(Taluk selectedTaluk) {
        allTalukEventList.setValue(CommonUtils.getTalukEventList(selectedTaluk));
    }

    public MutableLiveData<List<Event>> getTalukEventList() {
        return allTalukEventList;
    }
}

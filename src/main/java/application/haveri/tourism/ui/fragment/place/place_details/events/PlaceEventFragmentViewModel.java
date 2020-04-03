package application.haveri.tourism.ui.fragment.place.place_details.events;

import androidx.lifecycle.MutableLiveData;

import application.haveri.tourism.data.DataManager;
import application.haveri.tourism.data.model.api.response.haveri_data.Event;
import application.haveri.tourism.data.model.api.response.haveri_data.Place;
import application.haveri.tourism.ui.base.BaseViewModel;
import application.haveri.tourism.utils.CommonUtils;
import application.haveri.tourism.utils.rx.SchedulerProvider;

import java.util.List;

public class PlaceEventFragmentViewModel extends BaseViewModel<iPlaceEventFragmentContract.iPlaceEventFragmentNavigator>
        implements iPlaceEventFragmentContract.iPlaceEventFragmentViewModel {

    private final MutableLiveData<List<Event>> allPlaceEventList = new MutableLiveData<>();

    public PlaceEventFragmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void setEventData(Place selectedPlace) {
        allPlaceEventList.setValue(CommonUtils.getPlaceEventList(selectedPlace));
    }

    public MutableLiveData<List<Event>> getPlaceEventList() {
        return allPlaceEventList;
    }
}

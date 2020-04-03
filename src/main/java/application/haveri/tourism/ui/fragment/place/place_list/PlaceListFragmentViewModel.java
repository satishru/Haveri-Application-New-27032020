package application.haveri.tourism.ui.fragment.place.place_list;

import androidx.lifecycle.MutableLiveData;

import application.haveri.tourism.data.DataManager;
import application.haveri.tourism.data.model.api.response.haveri_data.Place;
import application.haveri.tourism.ui.base.BaseViewModel;
import application.haveri.tourism.utils.rx.SchedulerProvider;

import java.util.List;

public class PlaceListFragmentViewModel extends BaseViewModel<iPlaceListFragmentContract.iPlaceListFragmentNavigator> implements
        iPlaceListFragmentContract.iPlaceListFragmentViewModel {

    private final MutableLiveData<List<Place>> placeList = new MutableLiveData<>();

    public PlaceListFragmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void startLoadingData(List<Place> placeList) {
        this.placeList.setValue(placeList);
    }

    public MutableLiveData<List<Place>> getPlaceList() {
        return placeList;
    }
}

package application.haveri.tourism.ui.activity.place;

import application.haveri.tourism.data.DataManager;
import application.haveri.tourism.data.model.api.response.haveri_data.District;
import application.haveri.tourism.data.model.api.response.haveri_data.Place;
import application.haveri.tourism.data.model.api.response.haveri_data.Taluk;
import application.haveri.tourism.ui.base.BaseViewModel;
import application.haveri.tourism.utils.AppConstants;
import application.haveri.tourism.utils.CommonUtils;
import application.haveri.tourism.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

public class PlaceActivityViewModel extends BaseViewModel<iPlaceActivityContract.iPlaceActivityNavigator>
        implements iPlaceActivityContract.iPlaceActivityViewModel {

    public PlaceActivityViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void startLoadingData() {
        if (getNavigator().isSinglePlaceShow()) {
            getNavigator().loadPlaceDetailFragment();
        } else {
            getNavigator().loadPlaceListFragment();
        }
    }

    @Override
    public List<Place> getPlaceList(District district, Taluk selectedTaluk) {
        List<Place> placeList = new ArrayList<>();
        if (district != null) {
            if (selectedTaluk != null) {
                placeList.addAll(selectedTaluk.getPlaces());
            } else {
                for (Taluk taluk : district.getTaluks()) {
                    placeList.addAll(taluk.getPlaces());
                }
            }
            placeList.addAll(
                    CommonUtils.mockList(new ArrayList<>(placeList), AppConstants.MOCK_LIST_SIZE));
        }
        return placeList;
    }
}

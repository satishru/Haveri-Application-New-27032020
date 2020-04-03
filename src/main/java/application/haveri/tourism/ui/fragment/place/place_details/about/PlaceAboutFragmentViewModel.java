package application.haveri.tourism.ui.fragment.place.place_details.about;

import application.haveri.tourism.data.DataManager;
import application.haveri.tourism.data.model.MapSingleObject;
import application.haveri.tourism.data.model.api.response.haveri_data.Place;
import application.haveri.tourism.ui.base.BaseViewModel;
import application.haveri.tourism.utils.CommonUtils;
import application.haveri.tourism.utils.Language;
import application.haveri.tourism.utils.rx.SchedulerProvider;

public class PlaceAboutFragmentViewModel extends BaseViewModel<iPlaceAboutFragmentContract.iPlaceAboutFragmentNavigator>
        implements iPlaceAboutFragmentContract.iPlaceAboutFragmentViewModel {

    public PlaceAboutFragmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void onMapClick(Place selectedPlace) {
        if (selectedPlace != null) {
            MapSingleObject singleObject = new MapSingleObject();
            singleObject.setTitleEn(selectedPlace.getPlaceNameEn());
            singleObject.setTitleKn(selectedPlace.getPlaceNameKn());
            singleObject.setLatitude(selectedPlace.getLatitude());
            singleObject.setLongitude(selectedPlace.getLongitude());
            getNavigator().openMapSingleActivity(singleObject);
        }
    }

    public String getVisitingTime(Place selectedPlace) {
        String timeToVisitStr = "";
        if(selectedPlace != null) {
            String timeToVisit = (getSelectedLanguage() == Language.EN.getValue()) ? selectedPlace.getTimeToVisitEn() :
                    selectedPlace.getTimeToVisitKn();
            timeToVisit = timeToVisit.replace("_"," - ").replace("AnyTime","Any time");
            String[] splitTimings = timeToVisit.split(" - ");
            if(splitTimings.length >= 2) {
                if(splitTimings[0].equals(splitTimings[1]) ) {
                    timeToVisitStr = splitTimings[0];
                }
            }
        }
        return timeToVisitStr;
    }

    public void makePhoneCall(Place selectedPlace) {
        if(selectedPlace != null && !CommonUtils.checkNullOrEmpty(selectedPlace.getContactPhone())) {
            getNavigator().makePhoneCall(selectedPlace.getContactPhone());
        }
    }

    public void openWebLink(Place selectedPlace) {
        if(selectedPlace != null && !CommonUtils.checkNullOrEmpty(selectedPlace.getWebLink())) {
            getNavigator().openWebLink(selectedPlace.getWebLink());
        }
    }
}

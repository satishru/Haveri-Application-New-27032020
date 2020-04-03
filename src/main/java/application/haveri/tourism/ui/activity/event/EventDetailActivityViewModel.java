package application.haveri.tourism.ui.activity.event;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import application.haveri.tourism.data.DataManager;
import application.haveri.tourism.data.model.api.response.haveri_data.District;
import application.haveri.tourism.data.model.api.response.haveri_data.Event;
import application.haveri.tourism.data.remote.ApiEndPoint;
import application.haveri.tourism.ui.base.BaseViewModel;
import application.haveri.tourism.utils.CommonUtils;
import application.haveri.tourism.utils.Language;
import application.haveri.tourism.utils.rx.SchedulerProvider;

public class EventDetailActivityViewModel extends BaseViewModel<iEventDetailActivityContract.iEventDetailActivityNavigator>
        implements iEventDetailActivityContract.iEventDetailActivityViewModel {

    public ObservableField<String> eventTitle = new ObservableField<>("");
    public ObservableField<String> eventPlace = new ObservableField<>("");
    public ObservableField<String> eventAddress = new ObservableField<>("");
    public ObservableField<String> eventDate = new ObservableField<>("");
    public ObservableField<String> eventDetails = new ObservableField<>("");
    public ObservableField<String> eventImage = new ObservableField<>("");

    public ObservableBoolean isNavigationExits = new ObservableBoolean(false);
    public ObservableBoolean isImageExist = new ObservableBoolean(false);
    public ObservableBoolean isVideoExist = new ObservableBoolean(false);

    private Event event;

    public EventDetailActivityViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void setEventData(Event event, District district) {
        this.event = event;
        if (event != null && district != null) {
            isNavigationExits.set(CommonUtils.isValidCoordinates(event.getEventLatitude(),
                    event.getEventLongitude()));

            String eventPlaceFormatted;
            if (Language.EN.getValue() == getSelectedLanguage()) {
                eventPlaceFormatted = event.getPlaceId() == 0 ?
                        event.getEventPlaceNameEn() + ", " + event.getTalukNameEn() :
                        event.getPlaceNameEn() + ", " + event.getTalukNameEn();
                eventTitle.set(event.getEventNameEn());
                eventAddress.set(event.getEventAddressEn());
                eventDetails.set(event.getEventDescEn());
            } else {
                eventPlaceFormatted = event.getPlaceId() == 0 ?
                        event.getEventPlaceNameEn() + ", " + event.getTalukNameKn() :
                        event.getPlaceNameKn() + ", " + event.getTalukNameKn();
                eventTitle.set(event.getEventNameKn());
                eventAddress.set(event.getEventAddressKn());
                eventDetails.set(event.getEventDescKn());
            }
            eventPlace.set(eventPlaceFormatted);

            String image_url = null;
            if (!CommonUtils.checkNullOrEmpty(event.getEventImage())) {
                image_url = event.getEventImage();
                isImageExist.set(true);
            } else if (!CommonUtils.checkNullOrEmpty(event.getEventVideo())) {
                image_url = String.format(ApiEndPoint.YOUTUBE_THUMB_URL, event.getEventVideo());
                isVideoExist.set(true);
            }
            eventImage.set(image_url);

            String formatted_date = CommonUtils.formatEventDate(
                    event.getEventDateStart(), event.getEventDateEnd());
            eventDate.set(formatted_date);
        }
    }

    @Override
    public void onButtonPlayClick() {
        if (event != null) {
            getNavigator().playVideo();
        }
    }

    @Override
    public void onNavigateImageClick() {
        if (event != null) {
            getNavigator().navigateToMap();
        }
    }
}

package application.haveri.tourism.ui.fragment.home.adapter.event;

import androidx.databinding.ObservableField;

import application.haveri.tourism.data.model.api.response.haveri_data.Event;
import application.haveri.tourism.data.remote.ApiEndPoint;
import application.haveri.tourism.utils.CommonUtils;
import application.haveri.tourism.utils.Language;

public class HomeEventsAdapterViewModel {
    public ObservableField<String> eventTitle;
    public ObservableField<String> eventImage;
    public ObservableField<String> eventDate;

    private int position;
    private HomeEventsAdapterViewModelListener mListener;

    HomeEventsAdapterViewModel(int position, Event event, HomeEventsAdapterViewModelListener mListener, int language) {
        this.position = position;
        this.mListener = mListener;

        eventTitle = new ObservableField<>(
                Language.EN.getValue() == language ? event.getEventNameEn() :
                        event.getEventNameKn());

        String image_url = "";
        if (!CommonUtils.checkNullOrEmpty(event.getEventImage())) {
            image_url = event.getEventImage();
        } else if (!CommonUtils.checkNullOrEmpty(event.getEventVideo())) {
            image_url = String.format(ApiEndPoint.YOUTUBE_THUMB_URL, event.getEventVideo());
        }
        eventImage = new ObservableField<>(image_url);

        String formatted_date = CommonUtils.formatEventDate(event.getEventDateStart(),
                event.getEventDateEnd());
        eventDate = new ObservableField<>(formatted_date);
    }

    public void onEventClick() {
        if (mListener != null) {
            mListener.onEventClick(position);
        }
    }

    public interface HomeEventsAdapterViewModelListener {
        void onEventClick(int position);
    }
}

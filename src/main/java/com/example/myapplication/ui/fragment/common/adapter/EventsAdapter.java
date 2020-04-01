package com.example.myapplication.ui.fragment.common.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.data.model.api.response.haveri_data.Event;
import com.example.myapplication.databinding.LayoutEventItemBinding;
import com.example.myapplication.ui.base.BaseViewHolder;
import com.example.myapplication.utils.AppConstants;
import com.example.myapplication.utils.CommonUtils;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Event> eventList;
    private EventsAdapterListener eventsAdapterListener;

    public EventsAdapter(List<Event> eventList) {
        this.eventList = eventList;
    }

    public interface EventsAdapterListener {
        void onEventClick(Event event);
    }

    public void addItems(List<Event> eventList) {
        if (eventList != null) {
            clearItems();
            this.eventList.addAll((CommonUtils.mockList(eventList, AppConstants.MOCK_LIST_SIZE)));
            this.notifyDataSetChanged();
        }
    }

    public void clearItems() {
        eventList.clear();
    }

    public void setListener(EventsAdapterListener eventsAdapterListener) {
        this.eventsAdapterListener = eventsAdapterListener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutEventItemBinding layoutEventItemBinding = LayoutEventItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new TalukEventsViewHolder(layoutEventItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class TalukEventsViewHolder extends BaseViewHolder
            implements EventsAdapterViewModel.TalukEventsAdapterViewModelListener {

        LayoutEventItemBinding layoutEventItemBinding;

        TalukEventsViewHolder(LayoutEventItemBinding layoutEventItemBinding) {
            super(layoutEventItemBinding.getRoot());
            this.layoutEventItemBinding = layoutEventItemBinding;
        }

        @Override
        public void onBind(int position) {
            final Event event = eventList.get(position);
            EventsAdapterViewModel eventsAdapterViewModel = new EventsAdapterViewModel(position,
                    event, this,
                    getLanguage(layoutEventItemBinding));
            layoutEventItemBinding.setViewModel(eventsAdapterViewModel);
        }

        @Override
        public void onEventClick(int position) {
            if (eventsAdapterListener != null) {
                eventsAdapterListener.onEventClick(eventList.get(position));
            }
        }
    }
}

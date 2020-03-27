package com.example.myapplication.ui.fragment.home.adapter.event;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.data.model.api.response.haveri_data.Event;
import com.example.myapplication.databinding.LayoutHomeEventItemBinding;
import com.example.myapplication.ui.base.BaseViewHolder;
import com.example.myapplication.utils.AppConstants;
import com.example.myapplication.utils.CommonUtils;

import java.util.List;

public class HomeEventsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Event> eventList;
    private HomeEventsAdapterListener eventsAdapterListener;

    public HomeEventsAdapter(List<Event> eventList) {
        this.eventList = eventList;
    }

    public interface HomeEventsAdapterListener {
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

    public void setListener(HomeEventsAdapterListener eventsAdapterListener) {
        this.eventsAdapterListener = eventsAdapterListener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutHomeEventItemBinding layoutEventItemBinding = LayoutHomeEventItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new EventsViewHolder(layoutEventItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class EventsViewHolder extends BaseViewHolder
            implements HomeEventsAdapterViewModel.HomeEventsAdapterViewModelListener {

        LayoutHomeEventItemBinding layoutEventItemBinding;

        EventsViewHolder(LayoutHomeEventItemBinding layoutEventItemBinding) {
            super(layoutEventItemBinding.getRoot());
            this.layoutEventItemBinding = layoutEventItemBinding;
        }

        @Override
        public void onBind(int position) {
            final Event event = eventList.get(position);
            HomeEventsAdapterViewModel homeEventsAdapterViewModel = new HomeEventsAdapterViewModel(
                    position, event, this,
                    getLanguage(layoutEventItemBinding));
            layoutEventItemBinding.setViewModel(homeEventsAdapterViewModel);
        }

        @Override
        public void onEventClick(int position) {
            if (eventsAdapterListener != null) {
                eventsAdapterListener.onEventClick(eventList.get(position));
            }
        }
    }
}

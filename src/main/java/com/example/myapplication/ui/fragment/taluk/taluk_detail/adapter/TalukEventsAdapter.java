package com.example.myapplication.ui.fragment.taluk.taluk_detail.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.data.model.api.response.haveri_data.Event;
import com.example.myapplication.databinding.LayoutTalukEventItemBinding;
import com.example.myapplication.ui.base.BaseViewHolder;
import com.example.myapplication.utils.AppConstants;
import com.example.myapplication.utils.CommonUtils;

import java.util.List;

public class TalukEventsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Event> eventList;
    private TalukEventsAdapterListener talukEventsAdapterListener;

    public TalukEventsAdapter(List<Event> eventList) {
        this.eventList = eventList;
    }

    public interface TalukEventsAdapterListener {
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

    public void setListener(TalukEventsAdapterListener talukEventsAdapterListener) {
        this.talukEventsAdapterListener = talukEventsAdapterListener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutTalukEventItemBinding layoutTalukEventItemBinding = LayoutTalukEventItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new TalukEventsViewHolder(layoutTalukEventItemBinding);
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
            implements TalukEventsAdapterViewModel.TalukEventsAdapterViewModelListener {

        LayoutTalukEventItemBinding layoutTalukEventItemBinding;

        TalukEventsViewHolder(LayoutTalukEventItemBinding layoutTalukEventItemBinding) {
            super(layoutTalukEventItemBinding.getRoot());
            this.layoutTalukEventItemBinding = layoutTalukEventItemBinding;
        }

        @Override
        public void onBind(int position) {
            final Event event = eventList.get(position);
            TalukEventsAdapterViewModel talukEventsAdapterViewModel = new TalukEventsAdapterViewModel(position, event, this,
                    getLanguage(layoutTalukEventItemBinding));
            layoutTalukEventItemBinding.setViewModel(talukEventsAdapterViewModel);
        }

        @Override
        public void onEventClick(int position) {
            if(talukEventsAdapterListener != null) {
                talukEventsAdapterListener.onEventClick(eventList.get(position));
            }
        }
    }
}

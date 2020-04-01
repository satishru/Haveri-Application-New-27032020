package com.example.myapplication.ui.fragment.place.place_details.events;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.ui.fragment.common.adapter.EventsAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class PlaceEventFragmentModule {
    @Provides
    EventsAdapter provideEventsAdapter() {
        return new EventsAdapter(new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(PlaceEventFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }
}

package com.example.myapplication.ui.fragment.place.place_list;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.ui.fragment.place.place_list.adapter.PlaceAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class PlaceListFragmentModule {

    @Provides
    PlaceAdapter providePlaceListAdapter() { return new PlaceAdapter(new ArrayList<>()); }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(PlaceListFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }
}

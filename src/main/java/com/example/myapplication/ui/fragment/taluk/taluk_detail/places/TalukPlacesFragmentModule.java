package com.example.myapplication.ui.fragment.taluk.taluk_detail.places;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.ui.fragment.common.adapter.PlaceAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class TalukPlacesFragmentModule {
    @Provides
    PlaceAdapter providePlaceAdapter() {
        return new PlaceAdapter(new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(TalukPlacesFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }
}

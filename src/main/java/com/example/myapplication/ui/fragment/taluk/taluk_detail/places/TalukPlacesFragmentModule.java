package com.example.myapplication.ui.fragment.taluk.taluk_detail.places;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.ui.fragment.taluk.taluk_detail.adapter.TalukPlaceAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class TalukPlacesFragmentModule {
    @Provides
    TalukPlaceAdapter provideTalukEventsAdapter() {
        return new TalukPlaceAdapter(new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(TalukPlacesFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }
}

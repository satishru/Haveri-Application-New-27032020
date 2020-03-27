package com.example.myapplication.ui.fragment.taluk.taluk_detail.events;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.ui.fragment.taluk.taluk_detail.adapter.TalukEventsAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class TalukEventFragmentModule {

    @Provides
    TalukEventsAdapter provideTalukEventsAdapter() {
        return new TalukEventsAdapter(new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(TalukEventFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }
}

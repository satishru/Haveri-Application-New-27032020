package com.example.myapplication.ui.fragment.taluk.taluk_list;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.ui.fragment.taluk.taluk_list.adapter.TalukListAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class TalukListFragmentModule {

    @Provides
    TalukListAdapter provideTalukAdapter() { return new TalukListAdapter(new ArrayList<>()); }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(TalukListFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }
}

package com.example.myapplication.ui.fragment.taluk.taluk_detail.video;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.ui.fragment.common.adapter.VideoListAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class TalukVideosFragmentModule {

    @Provides
    VideoListAdapter provideVideoListAdapter() {
        return new VideoListAdapter(new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(TalukVideosFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }
}

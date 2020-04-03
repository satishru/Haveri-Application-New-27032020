package com.example.myapplication.ui.fragment.place.place_details.video;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.ui.fragment.common.adapter.VideoListAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class PlaceVideosFragmentModule {

    @Provides
    VideoListAdapter getVideoListAdapter() {
        return new VideoListAdapter(new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(PlaceVideosFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }
}

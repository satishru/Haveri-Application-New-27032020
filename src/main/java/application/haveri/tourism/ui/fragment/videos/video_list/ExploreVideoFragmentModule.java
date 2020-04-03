package application.haveri.tourism.ui.fragment.videos.video_list;

import androidx.recyclerview.widget.LinearLayoutManager;

import application.haveri.tourism.ui.fragment.common.adapter.VideoListAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class ExploreVideoFragmentModule {
    @Provides
    VideoListAdapter provideVideoListAdapter() {
        return new VideoListAdapter(new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(ExploreVideoFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }
}
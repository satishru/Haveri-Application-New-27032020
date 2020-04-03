package application.haveri.tourism.ui.fragment.taluk.taluk_detail.video;

import androidx.recyclerview.widget.LinearLayoutManager;

import application.haveri.tourism.ui.fragment.common.adapter.VideoListAdapter;

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

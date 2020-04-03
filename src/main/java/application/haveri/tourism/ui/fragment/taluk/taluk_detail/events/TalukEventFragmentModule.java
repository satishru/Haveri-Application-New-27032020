package application.haveri.tourism.ui.fragment.taluk.taluk_detail.events;

import androidx.recyclerview.widget.LinearLayoutManager;

import application.haveri.tourism.ui.fragment.common.adapter.EventsAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class TalukEventFragmentModule {

    @Provides
    EventsAdapter provideEventsAdapter() {
        return new EventsAdapter(new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(TalukEventFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }
}

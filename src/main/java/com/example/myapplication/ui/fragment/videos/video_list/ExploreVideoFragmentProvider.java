package com.example.myapplication.ui.fragment.videos.video_list;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ExploreVideoFragmentProvider {
    @ContributesAndroidInjector(modules = ExploreVideoFragmentModule.class)
    abstract ExploreVideoFragment provideExploreVideoFragment();
}

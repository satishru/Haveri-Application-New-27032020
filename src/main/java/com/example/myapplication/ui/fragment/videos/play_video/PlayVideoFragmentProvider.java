package com.example.myapplication.ui.fragment.videos.play_video;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PlayVideoFragmentProvider {
    @ContributesAndroidInjector
    abstract PlayVideoFragment providePlayVideoFragment();
}


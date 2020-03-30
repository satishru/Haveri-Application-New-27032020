package com.example.myapplication.ui.fragment.place.place_details;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PlaceDetailFragmentProvider {
    @ContributesAndroidInjector(modules = PlaceDetailFragmentModule.class)
    abstract PlaceDetailFragment providePlaceDetailFragment();
}

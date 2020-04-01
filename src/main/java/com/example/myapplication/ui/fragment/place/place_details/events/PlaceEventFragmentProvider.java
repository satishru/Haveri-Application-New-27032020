package com.example.myapplication.ui.fragment.place.place_details.events;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PlaceEventFragmentProvider {
    @ContributesAndroidInjector(modules = PlaceEventFragmentModule.class)
    abstract PlaceEventFragment getPlaceEventFragment();
}

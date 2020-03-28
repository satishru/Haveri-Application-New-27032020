package com.example.myapplication.ui.fragment.place.place_list;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PlaceListFragmentProvider {
    @ContributesAndroidInjector(modules = PlaceListFragmentModule.class)
    abstract PlaceListFragment providePlaceListFragment();
}
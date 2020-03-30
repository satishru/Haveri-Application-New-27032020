package com.example.myapplication.ui.fragment.place.place_details.about;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PlaceAboutFragmentProvider {
    @ContributesAndroidInjector
    abstract PlaceAboutFragment providePlaceAboutFragment();
}

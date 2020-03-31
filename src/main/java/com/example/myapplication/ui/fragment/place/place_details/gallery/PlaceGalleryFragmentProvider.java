package com.example.myapplication.ui.fragment.place.place_details.gallery;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PlaceGalleryFragmentProvider {
    @ContributesAndroidInjector(modules = PlaceGalleryFragmentModule.class)
    abstract PlaceGalleryFragment providePlaceGalleryFragment();
}

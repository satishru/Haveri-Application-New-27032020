package com.example.myapplication.ui.fragment.taluk.taluk_detail.places;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TalukPlacesFragmentProvider {
    @ContributesAndroidInjector(modules = TalukPlacesFragmentModule.class)
    abstract TalukPlacesFragment getTalukPlacesFragment();
}

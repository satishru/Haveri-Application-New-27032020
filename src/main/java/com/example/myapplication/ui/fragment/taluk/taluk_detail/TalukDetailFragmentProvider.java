package com.example.myapplication.ui.fragment.taluk.taluk_detail;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TalukDetailFragmentProvider {
    @ContributesAndroidInjector(modules = TalukDetailFragmentModule.class)
    abstract TalukDetailFragment provideTalukDetailFragment();
}

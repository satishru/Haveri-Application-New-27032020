package com.example.myapplication.ui.fragment.taluk.taluk_list;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TalukListFragmentProvider {
    @ContributesAndroidInjector(modules = TalukListFragmentModule.class)
    abstract TalukListFragment provideListFragment();
}

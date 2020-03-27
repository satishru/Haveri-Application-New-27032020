package com.example.myapplication.ui.fragment.taluk.taluk_detail.about;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TalukAboutFragmentProvider {
    @ContributesAndroidInjector
    abstract TalukAboutFragment provideTalukAboutFragment();
}

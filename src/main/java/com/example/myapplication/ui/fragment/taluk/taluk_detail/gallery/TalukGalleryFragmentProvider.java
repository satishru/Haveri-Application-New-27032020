package com.example.myapplication.ui.fragment.taluk.taluk_detail.gallery;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TalukGalleryFragmentProvider {
    @ContributesAndroidInjector(modules = TalukGalleryFragmentModule.class)
    abstract TalukGalleryFragment provideTalukGalleryFragment();
}

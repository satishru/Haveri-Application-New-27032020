package com.example.myapplication.di.builder;

import com.example.myapplication.ui.activity.event.EventDetailActivity;
import com.example.myapplication.ui.activity.home.HomeActivity;
import com.example.myapplication.ui.activity.map.MapSingleActivity;
import com.example.myapplication.ui.activity.media.image.ImageViewActivity;
import com.example.myapplication.ui.activity.media.image.ImageViewActivityModule;
import com.example.myapplication.ui.activity.media.video.VideosExploreActivity;
import com.example.myapplication.ui.activity.place.PlaceActivity;
import com.example.myapplication.ui.activity.setting.SettingActivity;
import com.example.myapplication.ui.activity.splash.SplashActivity;
import com.example.myapplication.ui.activity.taluk.TalukActivity;
import com.example.myapplication.ui.fragment.home.HomeFragmentProvider;
import com.example.myapplication.ui.fragment.place.place_details.PlaceDetailFragmentProvider;
import com.example.myapplication.ui.fragment.place.place_details.about.PlaceAboutFragmentProvider;
import com.example.myapplication.ui.fragment.place.place_details.events.PlaceEventFragmentProvider;
import com.example.myapplication.ui.fragment.place.place_details.gallery.PlaceGalleryFragmentProvider;
import com.example.myapplication.ui.fragment.place.place_list.PlaceListFragmentProvider;
import com.example.myapplication.ui.fragment.taluk.taluk_detail.TalukDetailFragmentProvider;
import com.example.myapplication.ui.fragment.taluk.taluk_detail.about.TalukAboutFragmentProvider;
import com.example.myapplication.ui.fragment.taluk.taluk_detail.events.TalukEventFragmentProvider;
import com.example.myapplication.ui.fragment.taluk.taluk_detail.gallery.TalukGalleryFragmentProvider;
import com.example.myapplication.ui.fragment.taluk.taluk_detail.places.TalukPlacesFragmentProvider;
import com.example.myapplication.ui.fragment.taluk.taluk_detail.video.TalukVideosFragmentProvider;
import com.example.myapplication.ui.fragment.taluk.taluk_list.TalukListFragmentProvider;
import com.example.myapplication.ui.fragment.videos.play_video.PlayVideoFragmentProvider;
import com.example.myapplication.ui.fragment.videos.video_list.ExploreVideoFragmentProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector(modules = HomeFragmentProvider.class)
    abstract HomeActivity bindHomeActivity();

    @ContributesAndroidInjector
    abstract MapSingleActivity bindMapSingleActivity();

    @ContributesAndroidInjector
    abstract SettingActivity bindSettingActivity();

    @ContributesAndroidInjector(modules = {
            TalukListFragmentProvider.class,
            TalukDetailFragmentProvider.class,
            TalukAboutFragmentProvider.class,
            TalukPlacesFragmentProvider.class,
            TalukGalleryFragmentProvider.class,
            TalukVideosFragmentProvider.class,
            TalukEventFragmentProvider.class
    })
    abstract TalukActivity bindTalukActivity();

    @ContributesAndroidInjector(modules = ImageViewActivityModule.class)
    abstract ImageViewActivity bindImageViewActivity();

    @ContributesAndroidInjector
    abstract EventDetailActivity bindEventDetailActivity();

    @ContributesAndroidInjector(modules = {
            ExploreVideoFragmentProvider.class,
            PlayVideoFragmentProvider.class
    })
    abstract VideosExploreActivity bindVideosExploreActivity();

    @ContributesAndroidInjector(modules = {
            PlaceListFragmentProvider.class,
            PlaceDetailFragmentProvider.class,
            PlaceAboutFragmentProvider.class,
            PlaceGalleryFragmentProvider.class,
            PlaceEventFragmentProvider.class
    })
    abstract PlaceActivity bindPlaceActivity();
}

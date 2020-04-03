package application.haveri.tourism.di.builder;

import application.haveri.tourism.ui.activity.event.EventDetailActivity;
import application.haveri.tourism.ui.activity.home.HomeActivity;
import application.haveri.tourism.ui.activity.map.MapSingleActivity;
import application.haveri.tourism.ui.activity.media.image.ImageViewActivity;
import application.haveri.tourism.ui.activity.media.image.ImageViewActivityModule;
import application.haveri.tourism.ui.activity.media.video.VideosExploreActivity;
import application.haveri.tourism.ui.activity.place.PlaceActivity;
import application.haveri.tourism.ui.activity.setting.SettingActivity;
import application.haveri.tourism.ui.activity.splash.SplashActivity;
import application.haveri.tourism.ui.activity.taluk.TalukActivity;
import application.haveri.tourism.ui.fragment.home.HomeFragmentProvider;
import application.haveri.tourism.ui.fragment.place.place_details.PlaceDetailFragmentProvider;
import application.haveri.tourism.ui.fragment.place.place_details.about.PlaceAboutFragmentProvider;
import application.haveri.tourism.ui.fragment.place.place_details.events.PlaceEventFragmentProvider;
import application.haveri.tourism.ui.fragment.place.place_details.gallery.PlaceGalleryFragmentProvider;
import application.haveri.tourism.ui.fragment.place.place_details.video.PlaceVideosFragmentProvider;
import application.haveri.tourism.ui.fragment.place.place_list.PlaceListFragmentProvider;
import application.haveri.tourism.ui.fragment.taluk.taluk_detail.TalukDetailFragmentProvider;
import application.haveri.tourism.ui.fragment.taluk.taluk_detail.about.TalukAboutFragmentProvider;
import application.haveri.tourism.ui.fragment.taluk.taluk_detail.events.TalukEventFragmentProvider;
import application.haveri.tourism.ui.fragment.taluk.taluk_detail.gallery.TalukGalleryFragmentProvider;
import application.haveri.tourism.ui.fragment.taluk.taluk_detail.places.TalukPlacesFragmentProvider;
import application.haveri.tourism.ui.fragment.taluk.taluk_detail.video.TalukVideosFragmentProvider;
import application.haveri.tourism.ui.fragment.taluk.taluk_list.TalukListFragmentProvider;
import application.haveri.tourism.ui.fragment.videos.play_video.PlayVideoFragmentProvider;
import application.haveri.tourism.ui.fragment.videos.video_list.ExploreVideoFragmentProvider;

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
            PlaceVideosFragmentProvider.class,
            PlaceEventFragmentProvider.class
    })
    abstract PlaceActivity bindPlaceActivity();
}

package application.haveri.tourism;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import application.haveri.tourism.data.DataManager;
import application.haveri.tourism.ui.activity.event.EventDetailActivityViewModel;
import application.haveri.tourism.ui.activity.home.HomeActivityViewModel;
import application.haveri.tourism.ui.activity.map.MapSingleActivityViewModel;
import application.haveri.tourism.ui.activity.media.image.ImageViewActivityViewModel;
import application.haveri.tourism.ui.activity.media.video.VideosExploreActivityViewModel;
import application.haveri.tourism.ui.activity.place.PlaceActivityViewModel;
import application.haveri.tourism.ui.activity.setting.SettingActivityViewModel;
import application.haveri.tourism.ui.activity.splash.SplashViewModel;
import application.haveri.tourism.ui.activity.taluk.TalukActivityViewModel;
import application.haveri.tourism.ui.fragment.home.HomeFragmentViewModel;
import application.haveri.tourism.ui.fragment.place.place_details.PlaceDetailFragmentViewModel;
import application.haveri.tourism.ui.fragment.place.place_details.about.PlaceAboutFragmentViewModel;
import application.haveri.tourism.ui.fragment.place.place_details.events.PlaceEventFragmentViewModel;
import application.haveri.tourism.ui.fragment.place.place_details.gallery.PlaceGalleryFragmentViewModel;
import application.haveri.tourism.ui.fragment.place.place_details.video.PlaceVideosFragmentViewModel;
import application.haveri.tourism.ui.fragment.place.place_list.PlaceListFragmentViewModel;
import application.haveri.tourism.ui.fragment.taluk.taluk_detail.TalukDetailFragmentViewModel;
import application.haveri.tourism.ui.fragment.taluk.taluk_detail.about.TalukAboutFragmentViewModel;
import application.haveri.tourism.ui.fragment.taluk.taluk_detail.events.TalukEventFragmentViewModel;
import application.haveri.tourism.ui.fragment.taluk.taluk_detail.gallery.TalukGalleryFragmentViewModel;
import application.haveri.tourism.ui.fragment.taluk.taluk_detail.places.TalukPlacesFragmentViewModel;
import application.haveri.tourism.ui.fragment.taluk.taluk_detail.video.TalukVideosFragmentViewModel;
import application.haveri.tourism.ui.fragment.taluk.taluk_list.TalukListFragmentViewModel;
import application.haveri.tourism.ui.fragment.videos.play_video.PlayVideoFragmentViewModel;
import application.haveri.tourism.ui.fragment.videos.video_list.ExploreVideoFragmentViewModel;
import application.haveri.tourism.utils.rx.SchedulerProvider;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    private final DataManager dataManager;
    private final SchedulerProvider schedulerProvider;

    @Inject
    public ViewModelProviderFactory(DataManager dataManager,
                                    SchedulerProvider schedulerProvider) {
        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
    }

    @NotNull
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {

        if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            return (T) new SplashViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(HomeActivityViewModel.class)) {
            return (T) new HomeActivityViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(HomeFragmentViewModel.class)) {
            return (T) new HomeFragmentViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(MapSingleActivityViewModel.class)) {
            return (T) new MapSingleActivityViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(SettingActivityViewModel.class)) {
            return (T) new SettingActivityViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(TalukActivityViewModel.class)) {
            return (T) new TalukActivityViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(TalukListFragmentViewModel.class)) {
            return (T) new TalukListFragmentViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(TalukDetailFragmentViewModel.class)) {
            return (T) new TalukDetailFragmentViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(TalukGalleryFragmentViewModel.class)) {
            return (T) new TalukGalleryFragmentViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(ImageViewActivityViewModel.class)) {
            return (T) new ImageViewActivityViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(TalukAboutFragmentViewModel.class)) {
            return (T) new TalukAboutFragmentViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(TalukVideosFragmentViewModel.class)) {
            return (T) new TalukVideosFragmentViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(TalukEventFragmentViewModel.class)) {
            return (T) new TalukEventFragmentViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(EventDetailActivityViewModel.class)) {
            return (T) new EventDetailActivityViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(VideosExploreActivityViewModel.class)) {
            return (T) new VideosExploreActivityViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(PlayVideoFragmentViewModel.class)) {
            return (T) new PlayVideoFragmentViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(ExploreVideoFragmentViewModel.class)) {
            return (T) new ExploreVideoFragmentViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(TalukPlacesFragmentViewModel.class)) {
            return (T) new TalukPlacesFragmentViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(PlaceActivityViewModel.class)) {
            return (T) new PlaceActivityViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(PlaceListFragmentViewModel.class)) {
            return (T) new PlaceListFragmentViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(PlaceDetailFragmentViewModel.class)) {
            return (T) new PlaceDetailFragmentViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(PlaceAboutFragmentViewModel.class)) {
            return (T) new PlaceAboutFragmentViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(PlaceGalleryFragmentViewModel.class)) {
            return (T) new PlaceGalleryFragmentViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(PlaceEventFragmentViewModel.class)) {
            return (T) new PlaceEventFragmentViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(PlaceVideosFragmentViewModel.class)) {
            return (T) new PlaceVideosFragmentViewModel(dataManager, schedulerProvider);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
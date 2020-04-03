package application.haveri.tourism.ui.fragment.place.place_details.video;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PlaceVideosFragmentProvider {
    @ContributesAndroidInjector(modules = PlaceVideosFragmentModule.class)
    abstract PlaceVideosFragment getPlaceVideosFragment();
}


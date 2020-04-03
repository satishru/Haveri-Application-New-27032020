package application.haveri.tourism.ui.fragment.place.place_details;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PlaceDetailFragmentProvider {
    @ContributesAndroidInjector
    abstract PlaceDetailFragment providePlaceDetailFragment();
}

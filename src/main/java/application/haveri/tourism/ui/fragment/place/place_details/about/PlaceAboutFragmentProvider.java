package application.haveri.tourism.ui.fragment.place.place_details.about;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PlaceAboutFragmentProvider {
    @ContributesAndroidInjector
    abstract PlaceAboutFragment providePlaceAboutFragment();
}

package application.haveri.tourism.ui.fragment.place.place_details.gallery;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PlaceGalleryFragmentProvider {
    @ContributesAndroidInjector(modules = PlaceGalleryFragmentModule.class)
    abstract PlaceGalleryFragment providePlaceGalleryFragment();
}

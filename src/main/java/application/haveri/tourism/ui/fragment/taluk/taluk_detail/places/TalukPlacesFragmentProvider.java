package application.haveri.tourism.ui.fragment.taluk.taluk_detail.places;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TalukPlacesFragmentProvider {
    @ContributesAndroidInjector(modules = TalukPlacesFragmentModule.class)
    abstract TalukPlacesFragment getTalukPlacesFragment();
}

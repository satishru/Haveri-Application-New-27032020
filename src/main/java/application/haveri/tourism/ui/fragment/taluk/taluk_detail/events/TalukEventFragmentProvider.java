package application.haveri.tourism.ui.fragment.taluk.taluk_detail.events;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TalukEventFragmentProvider {
    @ContributesAndroidInjector(modules = TalukEventFragmentModule.class)
    abstract TalukEventFragment getTalukEventFragment();
}

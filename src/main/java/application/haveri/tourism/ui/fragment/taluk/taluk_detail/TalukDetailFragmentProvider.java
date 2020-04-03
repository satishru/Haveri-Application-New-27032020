package application.haveri.tourism.ui.fragment.taluk.taluk_detail;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TalukDetailFragmentProvider {
    @ContributesAndroidInjector
    abstract TalukDetailFragment provideTalukDetailFragment();
}

package application.haveri.tourism.ui.fragment.taluk.taluk_detail.video;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TalukVideosFragmentProvider {
    @ContributesAndroidInjector(modules = TalukVideosFragmentModule.class)
    abstract TalukVideosFragment getTalukVideosFragment();
}

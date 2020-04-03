package application.haveri.tourism.ui.fragment.videos.play_video;

import application.haveri.tourism.data.DataManager;
import application.haveri.tourism.ui.base.BaseViewModel;
import application.haveri.tourism.utils.rx.SchedulerProvider;

public class PlayVideoFragmentViewModel extends BaseViewModel<iPlayVideoFragmentContract.iPlayVideoFragmentNavigator> implements
        iPlayVideoFragmentContract.iPlayVideoFragmentViewModel {

    public PlayVideoFragmentViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void onBackIconClicked() {
        getNavigator().onBackIconClicked();
    }
}

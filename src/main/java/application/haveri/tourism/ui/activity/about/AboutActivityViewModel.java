package application.haveri.tourism.ui.activity.about;

import application.haveri.tourism.data.DataManager;
import application.haveri.tourism.ui.base.BaseViewModel;
import application.haveri.tourism.utils.rx.SchedulerProvider;

public class AboutActivityViewModel extends BaseViewModel<iAboutActivityContract.iAboutActivityNavigator>
        implements iAboutActivityContract.iAboutActivityViewModel {
    public AboutActivityViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}

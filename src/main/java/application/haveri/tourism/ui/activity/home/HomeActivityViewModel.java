package application.haveri.tourism.ui.activity.home;

import application.haveri.tourism.data.DataManager;
import application.haveri.tourism.ui.base.BaseViewModel;
import application.haveri.tourism.utils.rx.SchedulerProvider;

public class HomeActivityViewModel extends BaseViewModel<iHomeActivityContract.iHomeActivityNavigator> implements
        iHomeActivityContract.iHomeActivityViewModel{

    public HomeActivityViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void onBackIconClicked() {
        getNavigator().onBackIconClicked();
    }
}

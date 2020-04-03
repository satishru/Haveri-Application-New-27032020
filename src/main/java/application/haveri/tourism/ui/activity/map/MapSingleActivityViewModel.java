package application.haveri.tourism.ui.activity.map;

import application.haveri.tourism.data.DataManager;
import application.haveri.tourism.ui.base.BaseNavigator;
import application.haveri.tourism.ui.base.BaseViewModel;
import application.haveri.tourism.utils.rx.SchedulerProvider;

public class MapSingleActivityViewModel extends BaseViewModel<BaseNavigator> {

    public MapSingleActivityViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}

package application.haveri.tourism.ui.activity.media.image;

import application.haveri.tourism.data.DataManager;
import application.haveri.tourism.ui.base.BaseViewModel;
import application.haveri.tourism.utils.rx.SchedulerProvider;

public class ImageViewActivityViewModel extends BaseViewModel<iImageViewActivityContract.iImageViewActivityNavigator> implements
        iImageViewActivityContract.iImageViewActivityViewModel {

    public ImageViewActivityViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void onBackIconClicked() {
        getNavigator().closeActivity();
    }
}

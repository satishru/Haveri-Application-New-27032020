package application.haveri.tourism.ui.activity.home;

import application.haveri.tourism.ui.base.BaseNavigator;

public interface iHomeActivityContract {
    interface iHomeActivityNavigator extends BaseNavigator {
        void onBackIconClicked();
    }

    interface iHomeActivityViewModel {
        void onBackIconClicked();
    }
}

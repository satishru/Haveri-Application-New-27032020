package application.haveri.tourism.ui.activity.media.image;

import application.haveri.tourism.ui.base.BaseNavigator;

class iImageViewActivityContract {

    interface iImageViewActivityNavigator extends BaseNavigator {
        void closeActivity();
    }

    interface iImageViewActivityViewModel {
        void onBackIconClicked();
    }
}

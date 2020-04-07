package application.haveri.tourism.ui.activity.setting;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;

import application.haveri.tourism.data.DataManager;
import application.haveri.tourism.ui.base.BaseViewModel;
import application.haveri.tourism.utils.AppUtils;
import application.haveri.tourism.utils.Language;
import application.haveri.tourism.utils.ViewUtils;
import application.haveri.tourism.utils.rx.SchedulerProvider;

public class SettingActivityViewModel extends BaseViewModel<iSettingActivityContract.iSettingActivityNavigator> implements
        iSettingActivityContract.iSettingActivityViewModel {

    private final MutableLiveData<Boolean> isLanguageChanged = new MutableLiveData<>();
    //private final MutableLiveData<Integer> selectedLanguageData = new MutableLiveData<>();

    public ObservableInt selectedLanguage = new ObservableInt(0);

    public SettingActivityViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void startInit() {
        int selected = getDataManager().getSelectedLanguage();
        selectedLanguage.set(selected);
        //selectedLanguageData.setValue(selected);
    }

    @Override
    public void onLanguageClicked(Language language) {
        getDataManager().setSelectedLanguage(language.getValue());
        //selectedLanguageData.setValue(language.getValue());
        isLanguageChanged.setValue(true);
        selectedLanguage.set(language.getValue());
        getNavigator().setLocale(language.getValue());
    }

    @Override
    public void clearImageCache() {
        getNavigator().clearImageCache();
    }

    @Override
    public void onFeedBackClicked() {
        getNavigator().openPlayStore();
    }

    @Override
    public void onAboutClicked() {
        getNavigator().openAboutActivity();
    }

    MutableLiveData<Boolean> getIsLanguageChanged() {
        return isLanguageChanged;
    }
}

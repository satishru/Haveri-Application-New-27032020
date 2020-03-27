package com.example.myapplication.ui.activity.setting;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.data.DataManager;
import com.example.myapplication.ui.base.BaseViewModel;
import com.example.myapplication.utils.Language;
import com.example.myapplication.utils.rx.SchedulerProvider;

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

    MutableLiveData<Boolean> getIsLanguageChanged() {
        return isLanguageChanged;
    }

/*    public MutableLiveData<Integer> getSelectedLanguageData() {
        return selectedLanguageData;
    }*/
}

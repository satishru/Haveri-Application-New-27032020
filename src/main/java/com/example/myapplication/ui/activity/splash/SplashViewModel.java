package com.example.myapplication.ui.activity.splash;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.R;
import com.example.myapplication.data.DataManager;
import com.example.myapplication.data.model.api.request.haveri_data.HaveriDataRequest;
import com.example.myapplication.data.model.api.response.BaseResponse;
import com.example.myapplication.data.model.db.HaveriData;
import com.example.myapplication.ui.base.BaseViewModel;
import com.example.myapplication.utils.CommonUtils;
import com.example.myapplication.utils.rx.SchedulerProvider;

import java.util.Date;

public class SplashViewModel extends BaseViewModel<iSplashActivityContract.iSplashNavigator> implements
        iSplashActivityContract.iSplashViewModel {

    private final MutableLiveData<BaseResponse> dataResponse = new MutableLiveData<>();

    public SplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void startLoading() {
        loading(true);
        getCompositeDisposable().add(getDataManager()
                .seedHaveriData()
                .flatMap(aBoolean -> getDataManager().seedHaveriData())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(this::startLoadingData, throwable -> {
                    loading(false);
                    openErrorDialog(throwable.getLocalizedMessage());
                }));
    }

    private void startLoadingData(boolean isLocalDataNotAvailable) {
        if(isLocalDataNotAvailable) {
            if(getNavigator().isNetWorkConnected()) {
                startLoadingServerData();
            } else {
                loading(false);
                getNavigator().openErrorDialog(R.drawable.ic_error,getNavigator().getStringFromId(R.string.label_error_no_internet));
            }
        } else {
            //startLoadingLocalData();
            loading(false);
            getNavigator().openHomeActivity();
        }
    }

    private void startLoadingServerData() {
        HaveriDataRequest request = new HaveriDataRequest();
        getCompositeDisposable().add(getDataManager()
                .doCallHaveriDataApiCall(request)
                .doOnSuccess(this::saveDataLocally)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(dataResponse -> {
                    loading(false);
                    if(dataResponse != null && !dataResponse.getSuccess()) {
                        openErrorDialog(dataResponse.getMessage());
                    } else {
                        this.dataResponse.setValue(dataResponse);
                    }
                },throwable ->{
                    loading(false);
                    openErrorDialog(throwable.getLocalizedMessage());
                }));
    }

    private void saveDataLocally(BaseResponse dataResponse) {
        if(dataResponse != null && dataResponse.getSuccess()) {
            HaveriData haveriData = new HaveriData();
            haveriData.createdAt  = new Date().toString();
            haveriData.updatedAt  = new Date().toString();
            haveriData.jsonData   = CommonUtils.stringToBase64(CommonUtils.convertObjToJson(dataResponse));

            getCompositeDisposable().add(getDataManager()
                    .deleteHaveriData()
                    .flatMap(aBoolean -> getDataManager().insertHaveriData(haveriData))
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(aBoolean -> {
                                loading(false);
                                getNavigator().openHomeActivity();
                            },
                            throwable -> {
                                loading(false);
                                openErrorDialog(throwable.getLocalizedMessage());
                            }));
        }
    }

    private void openErrorDialog(String errorMessage) {
        getNavigator().openErrorDialog(R.drawable.ic_error, errorMessage);
    }
}

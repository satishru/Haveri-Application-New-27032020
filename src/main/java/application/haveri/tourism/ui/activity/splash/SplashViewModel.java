package application.haveri.tourism.ui.activity.splash;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;

import application.haveri.tourism.R;
import application.haveri.tourism.data.DataManager;
import application.haveri.tourism.data.model.api.request.haveri_data.HaveriDataRequest;
import application.haveri.tourism.data.model.api.response.BaseResponse;
import application.haveri.tourism.data.model.db.HaveriData;
import application.haveri.tourism.ui.base.BaseViewModel;
import application.haveri.tourism.utils.AppLogger;
import application.haveri.tourism.utils.CommonUtils;
import application.haveri.tourism.utils.rx.SchedulerProvider;

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
        if (isLocalDataNotAvailable) {
            if (getNavigator().isNetWorkConnected()) {
                startLoadingServerData(false);
            } else {
                loading(false);
                getNavigator().openErrorDialog(R.drawable.ic_error,
                        getNavigator().getStringFromId(R.string.label_error_no_internet));
            }
        } else {
            if (getNavigator().isNetWorkConnected()) {
                startLoadingServerData(true);
            } else {
                openHomeActivity();
            }
        }
    }

    private void startLoadingServerData(boolean isReloadData) {
        HaveriDataRequest request = new HaveriDataRequest();
        request.setFcm_token(getNavigator().getFcmToken());
        AppLogger.d("DATA_REQUEST %s", new Gson().toJson(request));
        getCompositeDisposable().add(getDataManager()
                .doCallHaveriDataApiCall(request)
                .doOnSuccess(this::saveDataLocally)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(dataResponse -> {
                    loading(false);
                    if (isReloadData) {
                        openHomeActivity();
                    } else {
                        if (dataResponse != null && !dataResponse.getSuccess()) {
                            openErrorDialog(dataResponse.getMessage());
                        } else {
                            this.dataResponse.setValue(dataResponse);
                        }
                    }

                }, throwable -> {
                    if (isReloadData) {
                        openHomeActivity();
                    } else {
                        loading(false);
                        openErrorDialog("");
                    }
                }));
    }

    private void saveDataLocally(BaseResponse dataResponse) {
        if (dataResponse != null && dataResponse.getSuccess()) {
            HaveriData haveriData = new HaveriData();
            haveriData.createdAt = new Date().toString();
            haveriData.updatedAt = new Date().toString();
            haveriData.jsonData = CommonUtils.stringToBase64(
                    CommonUtils.convertObjToJson(dataResponse));

            getCompositeDisposable().add(getDataManager()
                    .deleteHaveriData()
                    .flatMap(aBoolean -> getDataManager().insertHaveriData(haveriData))
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(aBoolean -> openHomeActivity(),
                            throwable -> {
                                loading(false);
                                openErrorDialog(throwable.getLocalizedMessage());
                            }));
        }
    }

    private void openHomeActivity() {
        loading(false);
        getNavigator().openHomeActivity();
    }

    private void openErrorDialog(String errorMessage) {
        getNavigator().openErrorDialog(R.drawable.ic_error, errorMessage);
    }
}

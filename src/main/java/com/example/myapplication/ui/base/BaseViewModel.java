package com.example.myapplication.ui.base;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.DataManager;
import com.example.myapplication.utils.rx.SchedulerProvider;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel<N> extends ViewModel {

    private final DataManager mDataManager;

    private final ObservableBoolean mIsLoading = new ObservableBoolean();

    private final SchedulerProvider mSchedulerProvider;

    private CompositeDisposable mCompositeDisposable;

    private WeakReference<N> mNavigator;

    public BaseViewModel(DataManager dataManager,
                         SchedulerProvider schedulerProvider) {
        this.mDataManager = dataManager;
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    protected CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    private void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }

    protected N getNavigator() {
        return mNavigator.get();
    }

    public void setNavigator(N navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    protected void loading(boolean isLoading) {
        setIsLoading(isLoading);
        ((BaseNavigator)getNavigator()).showLoader(isLoading);
    }

    public int getSelectedLanguage() {
        return getDataManager().getSelectedLanguage();
    }
}

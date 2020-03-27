package com.example.myapplication.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.example.myapplication.HaveriApplication;
import com.example.myapplication.ViewModelProviderFactory;
import com.example.myapplication.ui.custom.ListSpacingItemDecorator;
import com.example.myapplication.utils.AppConstants;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel> extends Fragment implements
        BaseNavigator {

    @Inject
    public ViewModelProviderFactory factory;

    private BaseActivity mActivity;
    private T mViewDataBinding;
    private V mViewModel;

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    public abstract int getBindingVariable();

    /**
     * @return layout resource id
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    public abstract V getViewModel();

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        mViewModel = getViewModel();
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return mViewDataBinding.getRoot();
    }

    @Override
    public void onDetach() {
        showLoading(false);
        mActivity = null;
        super.onDetach();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.setLifecycleOwner(this);
        mViewDataBinding.executePendingBindings();
    }

    protected ListSpacingItemDecorator getVerticalDivider() {
        return new ListSpacingItemDecorator
                (getBaseActivity(), ListSpacingItemDecorator.VERTICAL_LIST,
                        AppConstants.LIST_SPACING_ITEM_MARGIN);
    }

    protected ListSpacingItemDecorator getHorizontalDivider() {
        return new ListSpacingItemDecorator
                (getBaseActivity(), ListSpacingItemDecorator.HORIZONTAL_LIST,
                        AppConstants.LIST_SPACING_ITEM_MARGIN);
    }

    protected BaseActivity getBaseActivity() {
        return mActivity;
    }

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    public boolean isNetworkConnected() {
        return mActivity != null && mActivity.isNetworkConnected();
    }

    public ActionBar getBaseSupportActionBar() {
        if (mActivity != null) {
            return mActivity.getSupportActionBar();
        }
        return null;
    }

    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }

    public void openActivityOnTokenExpire() {
        if (mActivity != null) {
            mActivity.openActivityOnTokenExpire();
        }
    }

    protected void startActivityWithAnimation(Intent intent) {
        if (mActivity != null) {
            mActivity.startActivityWithAnimation(intent);
        }
    }

    public void exitActivityWithAnimation() {
        if (mActivity != null) {
            mActivity.exitActivityWithAnimation();
        }
    }

    private void performDependencyInjection() {
        AndroidSupportInjection.inject(this);
    }

    private void showLoading(boolean isShow) {
        if (mActivity != null) {
            mActivity.showLoading(isShow);
        }
    }

    public void showToast(String message) {
        if (mActivity != null) {
            Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();
        }
    }

    protected boolean isDistrictNotNull() {
        return HaveriApplication.getInstance().getDistrict() != null;
    }

    public String getStringFromId(int string_id) {
        return getString(string_id);
    }

    @Override
    public void displayError(Throwable throwable) {
    }

    @Override
    public void displayErrorMessage(int errorCode, String errorMessage) {
    }

    @Override
    public void showLoader(boolean isShowLoader) {
        showLoading(isShowLoader);
    }

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}

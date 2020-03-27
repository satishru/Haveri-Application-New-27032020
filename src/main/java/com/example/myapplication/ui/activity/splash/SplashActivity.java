package com.example.myapplication.ui.activity.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.lifecycle.ViewModelProvider;
import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivitySplashBinding;
import com.example.myapplication.ui.activity.home.HomeActivity;
import com.example.myapplication.ui.base.BaseActivity;
import com.example.myapplication.ui.fragment.error.ErrorBottomSheetFragment;
import com.example.myapplication.utils.NetworkUtils;
import com.example.myapplication.utils.ViewAnimationUtils;

/**
 * Ref : // https://github.com/MindorksOpenSource/android-mvvm-architecture
 */
public class SplashActivity extends BaseActivity<ActivitySplashBinding , SplashViewModel> implements
        iSplashActivityContract.iSplashNavigator,
        ErrorBottomSheetFragment.ErrorBottomSheetFragmentCallBack {

    private SplashViewModel mSplashViewModel;

    @Override
    public int getBindingVariable() {
        return  BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public SplashViewModel getViewModel() {
        mSplashViewModel = new ViewModelProvider(this,factory).get(SplashViewModel.class);
        return mSplashViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        makeFullScreen();
        super.onCreate(savedInstanceState);
        mSplashViewModel.setNavigator(this);
        setAnimation();
        //DebugDB.getAddressLog();
        //subscribeToHaveriDataResponseLiveData();
        mSplashViewModel.startLoading();
        showLoader(true);
    }

    private void setAnimation() {
        ViewAnimationUtils.scaleAnimateView(getViewDataBinding().ivSplash,ViewAnimationUtils.SCALE_DURATION_300);
        Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        getViewDataBinding().tvSplash.startAnimation(animFadeIn);
        ViewAnimationUtils.scaleAnimateView(getViewDataBinding().tvSplash,ViewAnimationUtils.SCALE_DURATION_300);
    }

    /**
     * iSplashActivityContract.iSplashNavigator CallBacks
     */
    @Override
    public void openHomeActivity() {
        Intent intent = HomeActivity.newIntent(SplashActivity.this);
        new Handler().postDelayed(() -> {
            if(!isFinishing()) {
                startActivityWithAnimation(intent);
                finish();
            }
        }, 300);
    }

    @Override
    public void openErrorDialog(int drawable, String errorMessage) {
        ErrorBottomSheetFragment errorBottomSheetFragment = ErrorBottomSheetFragment.newInstance(drawable,errorMessage);
        errorBottomSheetFragment.show(getSupportFragmentManager(), errorBottomSheetFragment.getTag());
        errorBottomSheetFragment.setCancelable(false);
    }

    @Override
    public boolean isNetWorkConnected() {
        return NetworkUtils.isNetworkConnected(this);
    }
    /*iSplashActivityContract.iSplashNavigator CallBacks Ends*/

    /**
     * ErrorBottomSheetFragment.ErrorBottomSheetFragmentCallBack
     */
    @Override
    public void onRetryButtonClick() {
        mSplashViewModel.startLoading();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showLoading(false);
    }
}

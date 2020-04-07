package application.haveri.tourism.ui.activity.about;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import application.haveri.tourism.BR;
import application.haveri.tourism.R;
import application.haveri.tourism.databinding.ActivityAboutBinding;
import application.haveri.tourism.ui.base.BaseActivity;


/**
 * About the App
 */
public class AboutActivity extends BaseActivity<ActivityAboutBinding, AboutActivityViewModel> implements
        iAboutActivityContract.iAboutActivityNavigator {

    private AboutActivityViewModel aboutActivityViewModel;

    public static Intent newIntent(Activity activity) {
        return new Intent(activity, AboutActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    public AboutActivityViewModel getViewModel() {
        aboutActivityViewModel = new ViewModelProvider(this, factory).get(
                AboutActivityViewModel.class);
        return aboutActivityViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ActivityAboutBinding activityAboutBinding = getViewDataBinding();
        aboutActivityViewModel.setNavigator(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        exitActivityWithAnimation();
    }
}


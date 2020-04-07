package application.haveri.tourism.ui.activity.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import application.haveri.tourism.BR;
import application.haveri.tourism.R;
import application.haveri.tourism.databinding.ActivitySettingBinding;
import application.haveri.tourism.ui.activity.about.AboutActivity;
import application.haveri.tourism.ui.activity.setting.adapter.ThemeSelectionAdapter;
import application.haveri.tourism.ui.base.BaseActivity;
import application.haveri.tourism.utils.AppUtils;

public class SettingActivity extends BaseActivity<ActivitySettingBinding, SettingActivityViewModel> implements
        iSettingActivityContract.iSettingActivityNavigator,
        ThemeSelectionAdapter.ThemeSelectionAdapterListener {

    private SettingActivityViewModel settingActivityViewModel;
    private ActivitySettingBinding activitySettingBinding;
    private Thread thread;

    public static Intent newIntent(Activity activity) {
        Intent intent = new Intent(activity, SettingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public SettingActivityViewModel getViewModel() {
        settingActivityViewModel = new ViewModelProvider(this,factory).get(SettingActivityViewModel.class);
        return settingActivityViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySettingBinding = getViewDataBinding();
        setToolBar(activitySettingBinding.layoutToolbar.toolbar ,R.string.title_activity_settings, true);
        settingActivityViewModel.setNavigator(this);
        settingActivityViewModel.startInit();
        subscribeToLiveData();
        setupThemeAdapter();
    }

    private void subscribeToLiveData() {
        settingActivityViewModel.getIsLanguageChanged().observe(this,aBoolean -> {
            settingActivityViewModel.getDataManager().setIsScreenReloadRequired(aBoolean);
            if(aBoolean) {
                reCreateActivityWithoutAnimation();
            }
        });
    }

    private void setupThemeAdapter() {
        ThemeSelectionAdapter adapter = new ThemeSelectionAdapter(getAppTheme(), this);
        activitySettingBinding.rvThemeList.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            stopThread();
            exitActivityWithAnimation();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        stopThread();
        exitActivityWithAnimation();
    }

    private void stopThread() {
        if(thread != null && thread.isAlive() && !thread.isInterrupted()) {
            thread.interrupt();
        }
    }

    /**
     * iSettingActivityContract.iSettingActivityNavigator CallBacks
     * @param selectedLanguage Language selected
     */
    @Override
    public void setLocale(int selectedLanguage) {
        AppUtils.setLocale(this, selectedLanguage);
    }

    @Override
    public void clearImageCache() {
        Glide.get(this).clearMemory();
        thread = new Thread(() -> Glide.get(this).clearDiskCache());
        thread.start();
    }

    @Override
    public void openPlayStore() {
        AppUtils.openPlayStoreApp(this);
    }

    @Override
    public void openAboutActivity() {
        startActivityWithAnimation(AboutActivity.newIntent(this));
    }
    /*iSettingActivityContract.iSettingActivityNavigator CallBacks ends*/

    @Override
    public void updateSelectedTheme(int themeId) {
        settingActivityViewModel.getDataManager().setSelectedTheme(themeId);
        settingActivityViewModel.getDataManager().setIsScreenReloadRequired(true);
        reCreateActivityWithoutAnimation();
    }
}

package com.example.myapplication.ui.base;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.HaveriApplication;
import com.example.myapplication.R;
import com.example.myapplication.ViewModelProviderFactory;
import com.example.myapplication.data.local.prefs.AppPreferencesHelper;
import com.example.myapplication.ui.fragment.home.HomeFragment;
import com.example.myapplication.utils.AppConstants;
import com.example.myapplication.utils.AppUtils;
import com.example.myapplication.utils.Language;
import com.example.myapplication.utils.NetworkUtils;
import com.example.myapplication.utils.ScreenUtils;
import com.example.myapplication.utils.ViewUtils;

import java.util.Locale;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity
        implements BaseFragment.Callback, BaseNavigator, LocationListener {

    @Inject
    public ViewModelProviderFactory factory;

    @Inject
    public AppPreferencesHelper appPreferencesHelper;

    private ProgressDialog mProgressDialog;
    private T mViewDataBinding;
    private V mViewModel;
    public LocationManager locationManager;

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
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        updateTheme();
        super.onCreate(savedInstanceState);
        setOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        performDataBinding();
        //updateTheme();
        AppUtils.setLocale(getLanguage());
    }

    public void updateTheme() {
        int themeColorId = R.style.AppTheme;
        int SystemBarColor = R.color.colorPrimaryDark_Default;
        switch (getAppTheme()) {
            case ScreenUtils.THEME_ID_Default:
                themeColorId = R.style.AppTheme;
                SystemBarColor = R.color.colorPrimaryDark_Default;
                break;
            case ScreenUtils.THEME_ID_Dark:
                themeColorId = R.style.AppThemeDark;
                SystemBarColor = R.color.colorPrimaryDark_Dark;
                break;
            case ScreenUtils.THEME_ID_Red:
                themeColorId = R.style.AppThemeRed;
                SystemBarColor = R.color.colorPrimaryDark_Red;
                break;
            case ScreenUtils.THEME_ID_AmberYellow:
                themeColorId = R.style.AppThemeAmberYellow;
                SystemBarColor = R.color.colorPrimaryDark_AmberYellow;
                break;
            case ScreenUtils.THEME_ID_DeepBlue:
                themeColorId = R.style.AppThemeDeepBlue;
                SystemBarColor = R.color.colorPrimaryDark_DeepBlue;
                break;
        }
        setTheme(themeColorId);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(SystemBarColor));
        }
    }

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    public void setOrientation(int orientation) {
        setRequestedOrientation(orientation);
    }

    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    private void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
            mProgressDialog = null;
        }
    }

    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    public void openActivityOnTokenExpire() {
        //startActivity(LoginActivity.newIntent(this));
        //finish();
    }

    public void performDependencyInjection() {
        AndroidInjection.inject(this);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    public void checkPermissionAndGetLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (hasPermission(Manifest.permission.ACCESS_FINE_LOCATION) && hasPermission(
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                getLocation();
            } else {
                requestPermissionsSafely(
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        AppConstants.REQUEST_CODE_LOCATION_PERMISSION);
            }
        }
    }

    public String getProvider() {
        return locationManager.getBestProvider(new Criteria(), false);
    }

    public void getLocation() {
        if (hasPermission(Manifest.permission.ACCESS_FINE_LOCATION) && hasPermission(
                Manifest.permission.ACCESS_COARSE_LOCATION)) {
            // hasPermission() includes checkSelfPermission so added @SuppressLint("MissingPermission")
            if (locationManager != null) {
                @SuppressLint("MissingPermission")
                Location location = locationManager.getLastKnownLocation(getProvider());
                if (location != null) {
                    onLocationChanged(location);
                }
            }
        }
    }

    public void checkLocationOnAndGetLocation() {
        boolean isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkEnabled = locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER);

        if (!isGpsEnabled && !isNetworkEnabled) {
            new AlertDialog.Builder(this)
                    .setTitle("Enable Location Service")
                    .setMessage("Enable Location Service")
                    .setPositiveButton("Yes", (dialog, which) -> startActivityForResult(
                            new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS),
                            AppConstants.REQUEST_CODE_LOCATION_TURN_ON))
                    .setNegativeButton("No", (dialog, which) -> dialog.dismiss()).show();
        } else {
            getLocation();
        }
    }


    @SuppressLint("MissingPermission")
    public void startLocationUpdates() {
        if (locationManager != null) {
            if (hasPermission(Manifest.permission.ACCESS_FINE_LOCATION) && hasPermission(
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                locationManager.requestLocationUpdates(getProvider(),
                        AppConstants.LOCATION_UPDATE_TIME, AppConstants.LOCATION_UPDATE_DISTANCE,
                        this);
            }
        }
    }

    public void stopLocationUpdates() {
        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }

    public void showLoading(boolean isShow) {
        hideLoading();
        if (isShow) {
            mProgressDialog = ViewUtils.showLoadingDialog(this);
        }
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public String getStringFromId(int string_id) {
        return getString(string_id);
    }

    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }

    public void setToolBar(Toolbar toolBar, int title_id, boolean homeAsUpEnabled) {
        setSupportActionBar(toolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title_id);
            getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
        }
    }

    public void setToolBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    public void loadFragment(Fragment fragment, int container, boolean addToBackStack, boolean animate) {
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (animate) {
                transaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_left,
                        R.anim.slide_from_left, R.anim.slide_right);
            }
            transaction.replace(container, fragment);
            if (addToBackStack) {
                transaction.addToBackStack(null);
            }
            transaction.commitAllowingStateLoss();
        }
    }

    public int getLanguage() {
        if (mViewModel != null) {
            return mViewModel.getDataManager().getSelectedLanguage();
        }
        return Language.EN.getValue();
    }

    public int languageEnglish() {
        return Language.EN.getValue();
    }

    public int getAppTheme() {
        if (mViewModel != null) {
            return mViewModel.getDataManager().getSelectedTheme();
        } else if(appPreferencesHelper != null) {
            return appPreferencesHelper.getSelectedTheme();
        }
        return ScreenUtils.THEME_ID_Default;
    }

    protected boolean isDistrictNotNull() {
        return HaveriApplication.getInstance().getDistrict() != null;
    }

    public void reCreateActivityWithoutAnimation() {
        startActivity(getIntent());
        finish();
        overridePendingTransition(0, 0);
    }

    public void reCreateActivityWithAnimation() {
        startActivity(getIntent());
        exitActivityWithAnimation();
    }

    public void startActivityWithAnimation(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public void exitActivityWithAnimation() {
        finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    public int getBackStackEntryCount() {
        return getSupportFragmentManager().getBackStackEntryCount();
    }

    public void makeFullScreen() {
        // Remove Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Make Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Hide the toolbar
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    public void popBackStack() {
        getSupportFragmentManager().popBackStack();
    }

    public void closeBottomSheet() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment != null && fragment.isVisible() && fragment instanceof HomeFragment) {
            ((HomeFragment) fragment).openOrCloseBottomSheet();
        }
    }

    public boolean isBottomSheetOpened() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment != null && fragment.isVisible() && fragment instanceof HomeFragment) {
            return ((HomeFragment) fragment).isBottomSheetOpened();
        }
        return false;
    }

    public void navigateToMap(double latitude, double longitude) {
        if (latitude > 0 && longitude > 0) {
            String uri = "google.navigation:q=%f, %f";
            Intent navIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(String
                    .format(Locale.getDefault(), uri, latitude, longitude)));
            //navIntent.setPackage("com.google.android.apps.maps");
            if (navIntent.resolveActivity(this.getPackageManager()) != null) {
                startActivity(navIntent);
            } else {
                openInOtherBrowser(buildMapNavigateUrl(latitude, longitude, true));
            }
        }
    }

    /**
     * Create Map url -
     * if device location exists build url with source and destination cords
     * else build url with only destination cords to view in map
     *
     * @param latitude  Destination latitude
     * @param longitude Destination longitude
     * @return URL
     */
    private String buildMapNavigateUrl(double latitude, double longitude, boolean isNavigation) {
        String url = "http://maps.google.com/maps?";
        if (isNavigation) {
            Location srcLocation = HaveriApplication.getInstance().getLocation();
            if (srcLocation != null) {
                url = url + String.format(Locale.getDefault(), "saddr=%f, %f&daddr=%f, %f",
                        srcLocation.getLatitude(),
                        srcLocation.getLongitude(), latitude, longitude);
            } else {
                url = url + String.format(Locale.getDefault(), "daddr=%f, %f", latitude, longitude);
            }
        } else {
            url = url + String.format(Locale.getDefault(), "q=loc:%f, %f", latitude, longitude);
        }
        return url;
    }

    private void openInOtherBrowser(String url) {
        Intent browserIntent =
                Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER);
        browserIntent.setData(Uri.parse(url));
        browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            if (browserIntent.resolveActivity(getPackageManager()) != null) {
                //startActivity(browserIntent);
                startActivity(Intent.createChooser(browserIntent, "Open with"));
            } else {
                showToast("No app's found to open this link");
            }
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            showToast("No app's found to open this link");
        }
    }

    // BaseNavigator CallBacks
    @Override
    public void displayError(Throwable throwable) {
        if (throwable != null) {
            throwable.fillInStackTrace();
            showToast(throwable.getLocalizedMessage());
        }
    }

    @Override
    public void displayErrorMessage(int errorCode, String errorMessage) {
        // TODO handle error code here
        showToast(errorMessage);
    }

    @Override
    public void showLoader(boolean isShowLoader) {
        showLoading(isShowLoader);
    }


    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            HaveriApplication.getInstance().setLocation(location);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }
}


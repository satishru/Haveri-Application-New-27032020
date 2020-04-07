package application.haveri.tourism.ui.base;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.Locale;

import javax.inject.Inject;

import application.haveri.tourism.HaveriApplication;
import application.haveri.tourism.R;
import application.haveri.tourism.ViewModelProviderFactory;
import application.haveri.tourism.data.local.prefs.AppPreferencesHelper;
import application.haveri.tourism.data.model.api.response.haveri_data.District;
import application.haveri.tourism.ui.fragment.home.HomeFragment;
import application.haveri.tourism.utils.AppConstants;
import application.haveri.tourism.utils.AppUtils;
import application.haveri.tourism.utils.Language;
import application.haveri.tourism.utils.NetworkUtils;
import application.haveri.tourism.utils.ScreenUtils;
import application.haveri.tourism.utils.ViewUtils;
import dagger.android.AndroidInjection;

public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity
        implements BaseFragment.Callback, BaseNavigator {

    @Inject
    public ViewModelProviderFactory factory;

    @Inject
    public AppPreferencesHelper appPreferencesHelper;

    private T mViewDataBinding;
    private V mViewModel;
    private ProgressDialog mProgressDialog;

    private FusedLocationProviderClient mFusedLocationClient;

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
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        setOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        performDataBinding();
        AppUtils.setLocale(this, getLanguage());
    }

    public void updateTheme() {
        int themeColorId;
        //int systemBarColor;
        switch (getAppTheme()) {
            default:
            case ScreenUtils.THEME_ID_DarkPurple:
                themeColorId = R.style.AppThemeDarkPurple;
                //systemBarColor = R.color.colorPrimaryDark_DarkPurple;
                break;
            case ScreenUtils.THEME_ID_Tile:
                themeColorId = R.style.AppTheme;
                //systemBarColor = R.color.colorPrimaryDark_Default;
                break;
            case ScreenUtils.THEME_ID_Dark:
                themeColorId = R.style.AppThemeDark;
                //systemBarColor = R.color.colorPrimaryDark_Dark;
                break;
            case ScreenUtils.THEME_ID_Red:
                themeColorId = R.style.AppThemeRed;
                //systemBarColor = R.color.colorPrimaryDark_Red;
                break;
            case ScreenUtils.THEME_ID_AmberYellow:
                themeColorId = R.style.AppThemeAmberYellow;
                //systemBarColor = R.color.colorPrimaryDark_AmberYellow;
                break;
            case ScreenUtils.THEME_ID_DeepBlue:
                themeColorId = R.style.AppThemeDeepBlue;
                //systemBarColor = R.color.colorPrimaryDark_DeepBlue;
                break;
        }
        setTheme(themeColorId);
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //getWindow().setStatusBarColor(getResources().getColor(systemBarColor));
        }*/
    }

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    public void setOrientation(int orientation) {
        setRequestedOrientation(orientation);
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
        if (string_id <= 0) {
            return "";
        }
        return getResources().getString(string_id);
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
            String title_str = getStringFromId(title_id);
            getSupportActionBar().setTitle(title_str);
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
        } else if (appPreferencesHelper != null) {
            return appPreferencesHelper.getSelectedTheme();
        }
        return ScreenUtils.THEME_ID_DarkPurple;
    }

    protected District getDistrictData() {
        return HaveriApplication.getInstance().getDistrict();
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
        /*// Make Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Hide the toolbar*/
        //Hide the status bar on Android 4.0 and Lower
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int visibility = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(visibility);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        /*
        View decorView = getWindow().getDecorView();
			// Show the status bar.
			int visibility = View.SYSTEM_UI_FLAG_VISIBLE;
			decorView.setSystemUiVisibility(visibility);
         */
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

    public void handleMapViewAndNavigation(double latitude, double longitude, boolean isNavigation) {
        if (isNavigation) {
            navigateToMap(latitude, longitude);
        } else {
            openInMap(latitude, longitude);
        }
    }

    public void openInMap(double latitude, double longitude) {
        if (latitude > 0 && longitude > 0) {
            //Uri mapUri = Uri.parse(String.format(Locale.getDefault(),"geo:%s,%s",latitude, longitude));
            Uri mapUri = Uri.parse(
                    String.format(Locale.getDefault(), "geo:0,0?q=%s,%s", latitude, longitude));

            Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
            //mapIntent.setPackage("com.google.android.apps.maps");
            if (mapIntent.resolveActivity(this.getPackageManager()) != null) {
                startActivity(mapIntent);
            } else {
                openInOtherBrowser(buildMapNavigateUrl(latitude, longitude, false));
            }
        }
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
        showToast(errorMessage);
    }

    @Override
    public void showLoader(boolean isShowLoader) {
        showLoading(isShowLoader);
    }

    /**
     * Location Related Methods Starts
     */
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @SuppressLint("MissingPermission")
    protected void getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        task -> {
                            Location location = task.getResult();
                            if (location == null) {
                                requestNewLocationData();
                            } else {
                                HaveriApplication.getInstance().setLocation(location);
                            }
                        }
                );
            } else {
                openLocationSettingDialog();
            }
        } else {
            requestPermissionsSafely(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    AppConstants.REQUEST_CODE_LOCATION_PERMISSION);
        }
    }

    private void openLocationSettingDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Enable Location Service")
                .setMessage("Enable Location Service")
                .setPositiveButton("Settings", (dialog, which) -> startActivityForResult(
                        new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS),
                        AppConstants.REQUEST_CODE_LOCATION_TURN_ON))
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss()).show();
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location location = locationResult.getLastLocation();
            if (location != null) {
                HaveriApplication.getInstance().setLocation(location);
                stopLocationUpdates();
            }
        }
    };

    private boolean checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    public void stopLocationUpdates() {
        //mFusedLocationClient.removeLocationUpdates(mLocationCallback);
    }

    protected boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(
                Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(
                LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER);
    }
    /* Location Related Methods Ends */
}


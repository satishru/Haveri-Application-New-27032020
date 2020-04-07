package application.haveri.tourism;

import android.app.Activity;
import android.location.Location;

import androidx.multidex.MultiDexApplication;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.bumptech.glide.Glide;
import application.haveri.tourism.data.model.api.response.haveri_data.District;
import application.haveri.tourism.di.components.DaggerAppComponent;
import application.haveri.tourism.utils.AppLogger;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class HaveriApplication extends MultiDexApplication implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    private Location location;

    private static HaveriApplication mApplication = null;

    public static HaveriApplication getInstance() {
        return mApplication ;
    }

    private District district;

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);

        AppLogger.init();

        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Glide.get(getApplicationContext()).clearMemory();
        new Thread(() -> Glide.get(getApplicationContext()).clearDiskCache()).start();
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        if(location != null) {
            this.location = location;
        }
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
}

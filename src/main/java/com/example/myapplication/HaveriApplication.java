package com.example.myapplication;

import android.app.Activity;
import android.content.res.Configuration;
import android.location.Location;

import androidx.multidex.MultiDexApplication;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.bumptech.glide.Glide;
import com.example.myapplication.data.model.api.response.haveri_data.District;
import com.example.myapplication.di.components.DaggerAppComponent;
import com.example.myapplication.utils.AppLogger;
import com.example.myapplication.utils.AppUtils;

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

    //TODO
    // TalukVideosFragment Handle Internet Connection

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

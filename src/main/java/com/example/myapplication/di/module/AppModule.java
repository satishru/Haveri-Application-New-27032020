package com.example.myapplication.di.module;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.myapplication.BuildConfig;
import com.example.myapplication.data.AppDataManager;
import com.example.myapplication.data.DataManager;
import com.example.myapplication.data.local.db.AppDatabase;
import com.example.myapplication.data.local.db.AppDbHelper;
import com.example.myapplication.data.local.db.DbHelper;
import com.example.myapplication.data.local.prefs.AppPreferencesHelper;
import com.example.myapplication.data.local.prefs.PreferencesHelper;
import com.example.myapplication.data.remote.ApiHeader;
import com.example.myapplication.data.remote.ApiHelper;
import com.example.myapplication.data.remote.AppApiHelper;
import com.example.myapplication.di.ApiInfo;
import com.example.myapplication.di.DatabaseInfo;
import com.example.myapplication.di.PreferenceInfo;
import com.example.myapplication.utils.AppConstants;
import com.example.myapplication.utils.rx.AppSchedulerProvider;
import com.example.myapplication.utils.rx.SchedulerProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey,
                                                           PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(
                apiKey,
                preferencesHelper.getCurrentUserId(),
                preferencesHelper.getAccessToken());
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }
}

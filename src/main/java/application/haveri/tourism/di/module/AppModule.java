package application.haveri.tourism.di.module;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import application.haveri.tourism.BuildConfig;
import application.haveri.tourism.data.AppDataManager;
import application.haveri.tourism.data.DataManager;
import application.haveri.tourism.data.local.db.AppDatabase;
import application.haveri.tourism.data.local.db.AppDbHelper;
import application.haveri.tourism.data.local.db.DbHelper;
import application.haveri.tourism.data.local.prefs.AppPreferencesHelper;
import application.haveri.tourism.data.local.prefs.PreferencesHelper;
import application.haveri.tourism.data.remote.ApiHeader;
import application.haveri.tourism.data.remote.ApiHelper;
import application.haveri.tourism.data.remote.AppApiHelper;
import application.haveri.tourism.di.ApiInfo;
import application.haveri.tourism.di.DatabaseInfo;
import application.haveri.tourism.di.PreferenceInfo;
import application.haveri.tourism.utils.AppConstants;
import application.haveri.tourism.utils.rx.AppSchedulerProvider;
import application.haveri.tourism.utils.rx.SchedulerProvider;
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

package com.example.myapplication.data.local.db;

import com.example.myapplication.data.model.db.HaveriData;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppDbHelper implements DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    AppDbHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

    @Override
    public Observable<Boolean> isHaveriDataEmpty() {
        return mAppDatabase.haveriDataDao().getHaveriData()
                .flatMapObservable(data -> Observable.just(data.isEmpty()));
    }

    @Override
    public Observable<List<HaveriData>> getHaveriData() {
        return mAppDatabase.haveriDataDao().getHaveriData().toObservable();
    }

    @Override
    public Observable<Boolean> deleteHaveriData() {
        return Observable.fromCallable(() -> {
            mAppDatabase.haveriDataDao().deleteAll();
            return true;
        });
    }

    @Override
    public Observable<Boolean> insertHaveriData(HaveriData data) {
        return Observable.fromCallable(() -> {
            mAppDatabase.haveriDataDao().insert(data);
            return true;
        });
    }
}

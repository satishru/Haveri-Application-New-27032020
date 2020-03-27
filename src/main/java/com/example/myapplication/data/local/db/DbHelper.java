package com.example.myapplication.data.local.db;

import com.example.myapplication.data.model.db.HaveriData;

import java.util.List;

import io.reactivex.Observable;

public interface DbHelper {

    Observable<Boolean> isHaveriDataEmpty();

    Observable<Boolean> insertHaveriData(HaveriData data);

    Observable<List<HaveriData>> getHaveriData();

    Observable<Boolean> deleteHaveriData();
}

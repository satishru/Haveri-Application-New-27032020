package application.haveri.tourism.data.local.db;

import application.haveri.tourism.data.model.db.HaveriData;

import java.util.List;

import io.reactivex.Observable;

public interface DbHelper {

    Observable<Boolean> isHaveriDataEmpty();

    Observable<Boolean> insertHaveriData(HaveriData data);

    Observable<List<HaveriData>> getHaveriData();

    Observable<Boolean> deleteHaveriData();
}

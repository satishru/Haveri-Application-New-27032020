package application.haveri.tourism.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import application.haveri.tourism.data.model.db.HaveriData;
import application.haveri.tourism.utils.AppConstants;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface HaveriDataDao {

    @Query("DELETE FROM "+ AppConstants.DB_TABLE_DATA)
    void deleteAll();

    @Query("SELECT * FROM "+ AppConstants.DB_TABLE_DATA)
    Single<List<HaveriData>> getHaveriData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(HaveriData data);
}

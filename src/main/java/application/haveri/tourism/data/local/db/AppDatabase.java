package application.haveri.tourism.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import application.haveri.tourism.data.local.db.dao.HaveriDataDao;
import application.haveri.tourism.data.model.db.HaveriData;

@Database(entities = {HaveriData.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HaveriDataDao haveriDataDao();
}
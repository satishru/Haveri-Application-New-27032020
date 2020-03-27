package com.example.myapplication.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication.data.local.db.dao.HaveriDataDao;
import com.example.myapplication.data.model.db.HaveriData;

@Database(entities = {HaveriData.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HaveriDataDao haveriDataDao();
}
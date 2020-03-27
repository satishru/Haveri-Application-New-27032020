package com.example.myapplication.data.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.myapplication.utils.AppConstants;

@Entity(tableName = AppConstants.DB_TABLE_DATA)
public class HaveriData {

    @PrimaryKey
    public Long id;

    @ColumnInfo(name = "json_data")
    public String jsonData;

    @ColumnInfo(name = "created_at")
    public String createdAt;

    @ColumnInfo(name = "updated_at")
    public String updatedAt;
}
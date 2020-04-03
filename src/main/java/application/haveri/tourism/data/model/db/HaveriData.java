package application.haveri.tourism.data.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import application.haveri.tourism.utils.AppConstants;

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
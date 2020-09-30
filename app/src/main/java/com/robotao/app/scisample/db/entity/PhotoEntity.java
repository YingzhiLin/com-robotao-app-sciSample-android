package com.robotao.app.scisample.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Fts4;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.robotao.app.scisample.model.Photo;

import java.util.Date;

// Use `@Fts3` only if your app has strict disk space requirements or if you
// require compatibility with an older SQLite version.
// @Fts4 // 如果后面用了index，那就不能用Fts了
@Entity(tableName = "photos",
        foreignKeys = {
                @ForeignKey(entity = SampleEntity.class,
                        parentColumns = "rowid",
                        childColumns = "sample_id",
                        onDelete = ForeignKey.CASCADE)},
        indices = {@Index(value = "sample_id")
        })
public class PhotoEntity implements Photo {

    // Specifying a primary key for an FTS-table-backed entity is optional, but
    // if you include one, it must use this type and column name.
    @PrimaryKey(autoGenerate = true) // 这个属性是我自己添加，觉得这样省事
    @ColumnInfo(name = "rowid")
    public int id;

    @ColumnInfo(name = "sample_id") public int sampleID;
    @ColumnInfo(name = "asset_id") public String assetID;

    public String uri;
    @Embedded public Size size;
    public String thumb;
    @ColumnInfo(name = "creation_time") public long creationTime;
    public Date dt;
    public String exif;

    @Embedded public GPS gps;
    public String address;
}

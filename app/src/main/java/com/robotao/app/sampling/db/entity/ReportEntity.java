package com.robotao.app.sampling.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.robotao.app.sampling.model.Report;

import java.util.Date;


// Use `@Fts3` only if your app has strict disk space requirements or if you
// require compatibility with an older SQLite version.
// @Fts4 // 如果后面用了index，那就不能用Fts了
@Entity(tableName = "reports",
        foreignKeys = {
                @ForeignKey(entity = TaskEntity.class,
                        parentColumns = "id",
                        childColumns = "task_id",
                        onDelete = ForeignKey.CASCADE)},
        indices = {@Index(value = "task_id")
        })
public class ReportEntity implements Report {


    // Specifying a primary key for an FTS-table-backed entity is optional, but
    // if you include one, it must use this type and column name.
    @PrimaryKey(autoGenerate = true) // 这个属性是我自己添加，觉得这样省事
    @ColumnInfo(name = "rowid")
    public int id;

    @ColumnInfo(name = "task_id") public int taskID;
    @ColumnInfo(name = "asset_id") public String assetID;
    public String uri;
    public String type;
    public Date dt;
}

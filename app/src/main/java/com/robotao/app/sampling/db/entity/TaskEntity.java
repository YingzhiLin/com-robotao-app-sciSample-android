package com.robotao.app.sampling.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.robotao.app.sampling.model.Task;

import java.util.Date;

// Use `@Fts3` only if your app has strict disk space requirements or if you
// require compatibility with an older SQLite version.
// @Fts4
@Entity(tableName = "tasks")
// ,        indices = {@Index(value = {"first_name", "last_name"}, unique = true)}
public class TaskEntity implements Task {

    // Specifying a primary key for an FTS-table-backed entity is optional, but
    // if you include one, it must use this type and column name.
    @PrimaryKey(autoGenerate = true) // 这个属性是我自己添加，觉得这样省事
    public int id;

    // 因为数据库中不区分大小写，所以这儿用下划线进行区分，在sqlite中如此，在MySQL中亦如此，因此要区分
    @ColumnInfo(name = "task_name")
    public String taskName;
    @ColumnInfo(name = "dt_start")
    public Date dtStart;
    @ColumnInfo(name = "dt_end")
    public Date dtEnd;
    @ColumnInfo(name = "user_name")
    public String userName;
    @ColumnInfo(name = "sample_count")
    public int sampleCount;
    public boolean done; // 名称相同，因此不需要用@ColumnInfo定义

    @Override
    public int getId() {
        return id;
    }

    /*
      下面这些是用于创建一个task记录用的
     */
    public TaskEntity() {
    }

    @Ignore // 这后面的内容在测试时不会执行
    public TaskEntity(Task task) {
        this.id = task.getId();
    }
}

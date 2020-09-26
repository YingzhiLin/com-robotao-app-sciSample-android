package com.robotao.app.scisample.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.robotao.app.scisample.db.dao.TaskDao;
import com.robotao.app.scisample.db.entity.TaskEntity;

@Database(entities = {TaskEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
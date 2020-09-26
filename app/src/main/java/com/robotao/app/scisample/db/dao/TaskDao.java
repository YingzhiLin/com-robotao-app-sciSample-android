package com.robotao.app.scisample.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.robotao.app.scisample.db.entity.TaskEntity;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task")
    List<TaskEntity> getAll();

    @Query("SELECT * FROM task WHERE uid IN (:userIds)")
    List<TaskEntity> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM task WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    TaskEntity findByName(String first, String last);

    @Insert
    void insertAll(TaskEntity... tasks);

    @Delete
    void delete(TaskEntity taskEntity);
}
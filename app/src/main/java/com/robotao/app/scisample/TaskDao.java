package com.robotao.app.scisample;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Query("SELECT * FROM task WHERE uid IN (:userIds)")
    List<Task> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM task WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    Task findByName(String first, String last);

    @Insert
    void insertAll(Task... tasks);

    @Delete
    void delete(Task task);
}
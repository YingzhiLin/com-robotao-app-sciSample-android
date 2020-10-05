package com.robotao.app.sampling.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.robotao.app.sampling.db.entity.TaskEntity;
import com.robotao.app.sampling.db.entity.TaskWithReports;
import com.robotao.app.sampling.db.entity.TaskWithSamples;

import java.util.List;

@Dao
public interface TaskDao {

    /*
       在这儿需要将涉及到的sql语句一条条的对应起来
       感觉这个很麻烦啊
       可能是room提供了一系列的机制来做检查吧，我们所看到的只是面前看到的这些内容
     */
    @Query("SELECT * FROM tasks")
    List<TaskEntity> loadAll();
    @Query("SELECT * FROM tasks")
    LiveData<List<TaskEntity>> loadAllSync();

    @Query("SELECT * FROM tasks WHERE rowid IN (:ids)")
    List<TaskEntity> loadAllByIds(int[] ids);

    @Query("SELECT * FROM tasks WHERE rowid = :id LIMIT 1")
    TaskEntity loadById(int id);
    @Query("SELECT * FROM tasks WHERE rowid = :id LIMIT 1")
    LiveData<TaskEntity> loadByIdSync(int id);

//    @Query("SELECT * FROM tasks WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    TaskEntity findByName(String first, String last);

    // 定义关系查询
    @Transaction // Room需要查询两次，所以要放到一个事务中
    @Query("SELECT * FROM tasks WHERE rowid = :taskID")
    public List<TaskWithSamples> getTasksWithSamples(int taskID);

    @Transaction
    @Query("SELECT * FROM tasks WHERE rowid = :taskID")
    public List<TaskWithReports> getTaskWithReports(int taskID);

    @Insert
    void insertAll(TaskEntity... tasks);

    // 删除整个task项目，包括其下的所有内容
    @Transaction
    @Delete
    void deleteTask(TaskEntity taskEntity);
}
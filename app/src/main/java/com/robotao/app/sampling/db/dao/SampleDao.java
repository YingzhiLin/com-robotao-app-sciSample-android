package com.robotao.app.sampling.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.robotao.app.sampling.db.entity.SampleEntity;
import com.robotao.app.sampling.db.entity.SampleWithPhotos;
import com.robotao.app.sampling.db.entity.SampleWithVoices;

import java.util.List;

@Dao
public interface SampleDao {

    // 查找指定taskID对应的所有报告
    @Query("SELECT * FROM samples WHERE task_id = :taskID")
    List<SampleEntity> getSamplesByTaskID(int taskID);
    @Query("SELECT * FROM samples WHERE task_id = :taskID")
    LiveData<List<SampleEntity>> getSamplesByTaskIDSync(int taskID);

    // 根据ID查找对应的记录
    @Query("SELECT * FROM samples WHERE rowid = :id LIMIT 1")
    SampleEntity getSampleByID(int id);

    // 添加一个记录
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertSample(SampleEntity sample);

    // 修改一个记录
    @Update
    public int updateSample(SampleEntity sample);

    // 记录写入就不允许删除，只有在task被整体删除后才可以一起被删除
    @Ignore
    @Delete
    public int deleteSample(SampleEntity sample);

    // 联合检索
    @Query("SELECT * FROM samples WHERE rowid = :id")
    List<SampleEntity> loadById(int id);

    // 联合检索获得photos
    @Transaction
    @Query("SELECT * FROM samples WHERE rowid = :id")
    public List<SampleWithPhotos> getSampleWithPhotos(int id);

    // 联合检索获得voices
    @Transaction
    @Query("SELECT * FROM samples WHERE rowid = :id")
    public List<SampleWithVoices> getSampleWithVoices(int id);
}

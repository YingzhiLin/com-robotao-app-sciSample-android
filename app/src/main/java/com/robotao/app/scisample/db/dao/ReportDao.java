package com.robotao.app.scisample.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.robotao.app.scisample.db.entity.ReportEntity;

import java.util.List;

@Dao
public interface ReportDao {

    // 查找指定taskID对应的所有报告
    @Query("SELECT * FROM reports WHERE task_id = :taskID")
    List<ReportEntity> getReportsByTaskID(int taskID);

    // 根据ID查找对应的报告
    @Query("SELECT * FROM reports WHERE rowid = :id LIMIT 1")
    ReportEntity getReportByID(int id);

    // 添加一份新的报告
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertReport(ReportEntity report);

    // 删除一份报告
    @Delete
    public int deleteReport(ReportEntity report);
}

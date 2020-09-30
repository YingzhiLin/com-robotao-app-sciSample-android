package com.robotao.app.scisample.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.robotao.app.scisample.db.entity.VoiceEntity;

import java.util.List;

@Dao
public interface VoiceDao {

    // 查找指定sampleID对应的所有语音
    @Query("SELECT * FROM voices WHERE sample_id = :sampleID")
    List<VoiceEntity> getVoicesBySampleID(int sampleID);
    @Query("SELECT * FROM voices WHERE sample_id = :sampleID")
    LiveData<List<VoiceEntity>> getVoicesBySampleIDSync(int sampleID);

    // 根据photoID查找对应的语音
    @Query("SELECT * FROM voices WHERE rowid = :id LIMIT 1")
    VoiceEntity getVoiceByID(int id);

    // 添加一条新语音
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertVoice(VoiceEntity voice);

    // 修改语音的附属信息
    @Update
    public int updateVoice(VoiceEntity voice);

    // 删除一条语音
    @Ignore // 写入就不允许单独删除，存证所需，只能再task中整体删除
    @Delete
    public int deleteVoice(VoiceEntity voice);
}

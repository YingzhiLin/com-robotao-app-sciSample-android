package com.robotao.app.sampling.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.robotao.app.sampling.db.entity.PhotoEntity;

import java.util.List;

@Dao
public interface PhotoDao {

    // 查找指定sampleID对应的所有图片
    @Query("SELECT * FROM photos WHERE sample_id = :sampleID")
    List<PhotoEntity> getPhotosBySampleID(int sampleID);
    @Query("SELECT * FROM photos WHERE sample_id = :sampleID")
    LiveData<List<PhotoEntity>> getPhotosBySampleIDSync(int sampleID);

    // 根据photoID查找对应的图片
    @Query("SELECT * FROM photos WHERE rowid = :id LIMIT 1")
    PhotoEntity getPhotoByID(int id);

    // 添加一张新拍摄的照片
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertPhoto(PhotoEntity photo);

    // 修改一张照片的信息
    @Update
    public int updatePhoto(PhotoEntity photo);

    // 删除一张照片
    @Ignore // 一旦写入就不允许删除，这是存证所需。只能在task中整体删除
    @Delete
    public int deletePhoto(PhotoEntity photo);
}

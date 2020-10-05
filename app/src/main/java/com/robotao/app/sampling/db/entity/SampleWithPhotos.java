package com.robotao.app.sampling.db.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class SampleWithPhotos {
    @Embedded public SampleEntity sample;
    @Relation(
            parentColumn = "rowid",
            entityColumn = "sample_id"
    )
    public List<PhotoEntity> photos;
}

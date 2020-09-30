package com.robotao.app.scisample.db.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class SampleWithVoices {
    @Embedded public SampleEntity sample;
    @Relation(
            parentColumn = "rowid",
            entityColumn = "sample_id"
    )
    public List<VoiceEntity> voices;
}

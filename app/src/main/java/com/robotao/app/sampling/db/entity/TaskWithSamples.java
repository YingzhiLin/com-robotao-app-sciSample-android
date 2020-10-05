package com.robotao.app.sampling.db.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class TaskWithSamples {
    @Embedded public TaskEntity task;
    @Relation(
            parentColumn = "id",
            entityColumn = "task_id"
    )
    public List<SampleEntity> samples;
}

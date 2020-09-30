package com.robotao.app.scisample.db.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class TaskWithReports {
    @Embedded public TaskEntity task;
    @Relation(
            parentColumn = "id",
            entityColumn = "task_id"
    )
    public List<ReportEntity> reports;
}

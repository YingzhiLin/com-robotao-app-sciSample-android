package com.robotao.app.sampling.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.robotao.app.sampling.db.entity.TaskEntity;

import java.util.List;

public class Viewmodel_task extends ViewModel {
    private MutableLiveData<List<TaskEntity>> tasks;
    public LiveData<List<TaskEntity>> getTasks() {
        if (tasks == null) {
            tasks = new MutableLiveData<List<TaskEntity>>();
            loadTasks();
        }
        return tasks;
    }

    private void loadTasks() {
        // Do an asynchronous operation to fetch users.
    }
}
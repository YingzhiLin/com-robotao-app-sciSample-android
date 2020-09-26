package com.robotao.app.scisample;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class Viewmodel_task extends ViewModel {
    private MutableLiveData<List<Task>> tasks;
    public LiveData<List<Task>> getTasks() {
        if (tasks == null) {
            tasks = new MutableLiveData<List<Task>>();
            loadTasks();
        }
        return tasks;
    }

    private void loadTasks() {
        // Do an asynchronous operation to fetch users.
    }
}
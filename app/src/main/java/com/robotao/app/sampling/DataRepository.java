package com.robotao.app.sampling;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.robotao.app.sampling.db.AppDatabase;
import com.robotao.app.sampling.db.entity.TaskEntity;

import java.util.List;


/**
 * Repository handling the work with products and comments.
 */
public class DataRepository {

    private static DataRepository sInstance;

    private final AppDatabase mDatabase;
    private MediatorLiveData<List<TaskEntity>> mObservableTasks;

    private DataRepository(final AppDatabase database) {
        mDatabase = database;
        mObservableTasks = new MediatorLiveData<>();

        mObservableTasks.addSource(mDatabase.taskDao().loadAllSync(),
                taskEntities -> {
                    if (mDatabase.getDatabaseCreated().getValue() != null) {
                        mObservableTasks.postValue(taskEntities);
                    }
                });
    }

    public static DataRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(database);
                }
            }
        }
        return sInstance;
    }

    /**
     * Get the list of products from the database and get notified when the data changes.
     */
    public LiveData<List<TaskEntity>> getTasks() {
        return mObservableTasks;
    }

    public LiveData<TaskEntity> loadTaskSync(final int taskId) {
        return mDatabase.taskDao().loadByIdSync(taskId);
    }

//    public LiveData<List<ProductEntity>> searchProducts(String query) {
//        return mDatabase.productDao().searchAllProducts(query);
//    }
}

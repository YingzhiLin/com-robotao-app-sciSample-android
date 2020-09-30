package com.robotao.app.scisample.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.robotao.app.scisample.AppExecutors;
import com.robotao.app.scisample.db.converter.DateConverter;
import com.robotao.app.scisample.db.dao.PhotoDao;
import com.robotao.app.scisample.db.dao.ReportDao;
import com.robotao.app.scisample.db.dao.SampleDao;
import com.robotao.app.scisample.db.dao.TaskDao;
import com.robotao.app.scisample.db.dao.VoiceDao;
import com.robotao.app.scisample.db.entity.PhotoEntity;
import com.robotao.app.scisample.db.entity.ReportEntity;
import com.robotao.app.scisample.db.entity.SampleEntity;
import com.robotao.app.scisample.db.entity.TaskEntity;
import com.robotao.app.scisample.db.entity.VoiceEntity;

import java.util.List;

@Database(entities = {TaskEntity.class, ReportEntity.class, SampleEntity.class, PhotoEntity.class, VoiceEntity.class}, version = 2)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase sInstance;

    @VisibleForTesting
    public static final String DATABASE_NAME = "com_robotao_app_scisample_db_test";

    public abstract TaskDao taskDao();
    public abstract ReportDao reportDao();
    public abstract SampleDao sampleDao();
    public abstract PhotoDao photoDao();
    public abstract VoiceDao voiceDao();

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public static AppDatabase getInstance(final Context context, final AppExecutors executors) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext(), executors);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    /**
     * Build the database. {@link Builder#build()} only sets up the database configuration and
     * creates a new instance of the database.
     * The SQLite database is only created when it's accessed for the first time.
     */
    private static AppDatabase buildDatabase(final Context appContext,
                                             final AppExecutors executors) {
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        executors.diskIO().execute(() -> {
                            /* 在程序第一次使用的时候创建一个基础数据库，并非一定需要
                            // Add a delay to simulate a long-running operation
                            addDelay();

                            // Generate the data for pre-population
                            AppDatabase database = AppDatabase.getInstance(appContext, executors);
                            List<ProductEntity> products = DataGenerator.generateProducts();
                            List<CommentEntity> comments =
                                    DataGenerator.generateCommentsForProducts(products);

                            insertData(database, products, comments);
                            */

                            AppDatabase database = AppDatabase.getInstance(appContext, executors);
                            // notify that the database was created and it's ready to be used
                            database.setDatabaseCreated();
                        });
                    }
                })
                //.addMigrations(MIGRATION_1_2)
                .build();
    }

    /**
     * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
     */
    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true);
    }

    /*
    private static void insertData(final AppDatabase database, final List<ProductEntity> products,
                                   final List<CommentEntity> comments) {
        database.runInTransaction(() -> {
            database.productDao().insertAll(products);
            database.commentDao().insertAll(comments);
        });
    }*/

    private static void addDelay() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ignored) {
        }
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }

    /*
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {

        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE VIRTUAL TABLE IF NOT EXISTS `productsFts` USING FTS4("
                    + "`name` TEXT, `description` TEXT, content=`products`)");
            database.execSQL("INSERT INTO productsFts (`rowid`, `name`, `description`) "
                    + "SELECT `id`, `name`, `description` FROM products");

        }
    };  */

}
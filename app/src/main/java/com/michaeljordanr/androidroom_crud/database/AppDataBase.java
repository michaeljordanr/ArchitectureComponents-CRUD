package com.michaeljordanr.androidroom_crud.database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.michaeljordanr.androidroom_crud.person.db.dao.AddressDao;
import com.michaeljordanr.androidroom_crud.person.db.dao.CatDao;
import com.michaeljordanr.androidroom_crud.person.db.dao.PersonDao;
import com.michaeljordanr.androidroom_crud.person.db.entity.AddressEntity;
import com.michaeljordanr.androidroom_crud.person.db.entity.CatEntity;
import com.michaeljordanr.androidroom_crud.person.db.entity.PersonEntity;

/**
 * Created by husaynhakeem on 6/12/17.
 */

@Database(entities = {PersonEntity.class, CatEntity.class, AddressEntity.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance;

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public static final String DATABASE_NAME = "basic-sample-db";

    public abstract CatDao catDao();
    public abstract PersonDao personDao();
    public abstract AddressDao addressDao();


    public static AppDataBase getAppDatabase(Context context) {
        if (instance == null) {
            synchronized (AppDataBase.class) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDataBase.class,
                        DATABASE_NAME)
                        .allowMainThreadQueries()
                        .build();

                instance.setDatabaseCreated();
            }
        }
        return instance;
    }

    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }
}

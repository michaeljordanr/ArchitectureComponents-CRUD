package com.michaeljordanr.androidroom_crud;

import android.app.Application;

import com.michaeljordanr.androidroom_crud.database.AppDataBase;

public class BasicApp extends Application{
    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppExecutors = new AppExecutors();
    }

    public AppDataBase getDatabase() {
        return AppDataBase.getAppDatabase(this);
    }

    public DataRepository getRepository() {
        return DataRepository.getInstance(getDatabase());
    }
}

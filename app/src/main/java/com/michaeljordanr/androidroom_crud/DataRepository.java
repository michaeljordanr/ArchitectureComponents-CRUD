package com.michaeljordanr.androidroom_crud;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;


import java.util.List;

import com.michaeljordanr.androidroom_crud.database.AppDataBase;
import com.michaeljordanr.androidroom_crud.person.db.entity.PersonEntity;
import com.michaeljordanr.androidroom_crud.person.model.Person;

public class DataRepository {

    private static DataRepository sInstance;

    private final AppDataBase mDatabase;
    private MediatorLiveData<List<PersonEntity>> mObservablePerson;

    private DataRepository(final AppDataBase database) {
        mDatabase = database;
        mObservablePerson = new MediatorLiveData<>();

        mObservablePerson.addSource(mDatabase.personDao().loadAll(),
                personEntities -> {
                    if (mDatabase.getDatabaseCreated().getValue() != null) {
                        mObservablePerson.postValue(personEntities);
                    }
                });
    }

    public static DataRepository getInstance(final AppDataBase database) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(database);
                }
            }
        }
        return sInstance;
    }

//    /**
//     * Get the list of products from the database and get notified when the data changes.
//     */
//    public LiveData<List<ProductEntity>> getProducts() {
//        return mObservableProducts;
//    }
//
//    public LiveData<ProductEntity> loadProduct(final int productId) {
//        return mDatabase.productDao().loadProduct(productId);
//    }
//
    public LiveData<List<PersonEntity>> loadPeople() {
        return mDatabase.personDao().loadAll();
    }

    public void insertPerson(PersonEntity person){
        mDatabase.personDao().insert(person);
    }

    public void deleteAll(){
        mDatabase.personDao().deleteAll();
    }
}
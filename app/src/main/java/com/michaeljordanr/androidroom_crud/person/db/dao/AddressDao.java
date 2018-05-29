package com.michaeljordanr.androidroom_crud.person.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.michaeljordanr.androidroom_crud.person.db.entity.AddressEntity;

/**
 * Created by husaynhakeem on 6/12/17.
 */

@Dao
public interface AddressDao {

    @Insert
    void insert(AddressEntity... address);

    @Update
    void update(AddressEntity... address);

    @Delete
    void delete(AddressEntity... address);

    @Query("Select * FROM address")
    AddressEntity[] loadAll();
}
package com.michaeljordanr.androidroom_crud.person.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.michaeljordanr.androidroom_crud.person.db.entity.CatEntity;

/**
 * Created by husaynhakeem on 6/12/17.
 */

@Dao
public interface CatDao {

    @Insert
    void insert(CatEntity... catEntity);

    @Update
    void update(CatEntity... catEntity);

    @Delete
    void delete(CatEntity... catEntity);

    @Query("Select * FROM cat")
    CatEntity[] loadAll();

    @Query("Select * FROM cat WHERE hooman_id == :owner")
    CatEntity[] loadAllCatOwners(int owner);
}

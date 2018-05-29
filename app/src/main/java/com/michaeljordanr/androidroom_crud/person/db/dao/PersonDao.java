package com.michaeljordanr.androidroom_crud.person.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.michaeljordanr.androidroom_crud.person.db.entity.PersonEntity;


/**
 * Created by husaynhakeem on 6/12/17.
 */

@Dao
public interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PersonEntity... person);

    @Update
    void update(PersonEntity... person);

    @Delete
    void delete(PersonEntity... person);

    @Query("delete from person")
    void deleteAll();

    @Query("Select * FROM person")
    LiveData<List<PersonEntity>> loadAll();
}

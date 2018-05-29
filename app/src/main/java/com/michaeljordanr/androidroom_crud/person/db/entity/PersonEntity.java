package com.michaeljordanr.androidroom_crud.person.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;

import com.michaeljordanr.androidroom_crud.person.model.Person;

/**
 * Created by husaynhakeem on 6/12/17.
 */

@Entity (tableName = "person")
public class PersonEntity implements Person {

    @PrimaryKey (autoGenerate = true)
    public int id;

    @ColumnInfo (name = "first_name")
    public String firstName;

    @ColumnInfo (name = "last_name")
    public String lastName;

    @Ignore
    public Bitmap picture;

    @Embedded
    public AddressEntity address;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public Bitmap getPicture() {
        return picture;
    }

    @Override
    public AddressEntity getAddress() {
        return address;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }
}
package com.michaeljordanr.androidroom_crud.person.model;

import android.graphics.Bitmap;

import com.michaeljordanr.androidroom_crud.person.db.entity.AddressEntity;

public interface Person {

    int getId();
    String getFirstName();
    String getLastName();
    Bitmap getPicture();
    AddressEntity getAddress();

}

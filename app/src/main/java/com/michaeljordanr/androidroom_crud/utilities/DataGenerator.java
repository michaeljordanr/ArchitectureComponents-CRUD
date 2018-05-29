package com.michaeljordanr.androidroom_crud.utilities;

import com.michaeljordanr.androidroom_crud.database.AppDataBase;
import com.michaeljordanr.androidroom_crud.person.db.entity.AddressEntity;
import com.michaeljordanr.androidroom_crud.person.db.entity.PersonEntity;

/**
 * Created by husaynhakeem on 6/12/17.
 */

public class DataGenerator {


    private static DataGenerator instance;
    private static AppDataBase dataBase;


    public static DataGenerator with(AppDataBase appDataBase) {

        if (dataBase == null)
            dataBase = appDataBase;

        if (instance == null)
            instance = new DataGenerator();

        return instance;
    }


    public void generatePeople() {
        if (dataBase == null)
            return;

        PersonEntity[] people = new PersonEntity[4];
        people[0] = personInstance(5, "Husayn", "Hakeem");
        people[1] = personInstance(6, "Afafe", "Mokhtari");
        people[2] = personInstance(7, "Abderrahmane", "Bachiri");
        people[3] = personInstance(8, "Jalal", "Khaldouni");

        dataBase.personDao().insert(people);
    }


    private AddressEntity addressInstance(String street, String state, String city, int postCode) {
        AddressEntity address = new AddressEntity();

        address.street = street;
        address.state = state;
        address.city = city;
        address.postCode = postCode;

        return address;
    }


    private PersonEntity personInstance(int id, String firstName, String lastName) {
        PersonEntity person = new PersonEntity();

        person.id = id;
        person.firstName = firstName;
        person.lastName = lastName;

        AddressEntity address = addressInstance("Some street name", "Some state", "Some city", 19421);
       // dataBase.addressDao().insert(address);

        person.address = address;

        return person;
    }


//    public void generateCats() {
//
//        if (dataBase == null)
//            return;
//
//        CatEntity[] cats = new CatEntity[5];
//        cats[0] = catInstance("Tony", 3, 1);
//        cats[1] = catInstance("Tiger", 1, 1);
//        cats[2] = catInstance("Misty", 2, 2);
//        cats[3] = catInstance("Oscar", 5, 3);
//        cats[4] = catInstance("Puss", 4, 4);
//
//        dataBase.catDao().insert(cats);
//    }
//
//
//    private CatEntity catInstance(String name, int age, int owner) {
//        CatEntity cat = new CatEntity();
//
//        cat.name = name;
//        cat.age = age;
//        cat.hoomanId = owner;
//
//        return cat;
//    }
}

package com.michaeljordanr.androidroom_crud.person.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.List;

import com.michaeljordanr.androidroom_crud.BasicApp;
import com.michaeljordanr.androidroom_crud.DataRepository;
import com.michaeljordanr.androidroom_crud.person.db.entity.PersonEntity;

public class PersonViewModel extends ViewModel {
    private final LiveData<List<PersonEntity>> observablePerson;
    private DataRepository repository;

    public PersonViewModel(DataRepository repository){
        observablePerson = repository.loadPeople();
        this.repository = repository;
    }

    public LiveData<List<PersonEntity>> getObservablePersons() {
        return observablePerson;
    }

    public void insert(PersonEntity person){
        repository.insertPerson(person);
    }

    public void deleteAll(){
        repository.deleteAll();
        synchronized(observablePerson){
            observablePerson.notify();
        }
    }

    /**
     * A creator is used to inject the person ID into the ViewModel
     * <p>
     * This creator is to showcase how to inject dependencies into ViewModels. It's not
     * actually necessary in this case, as the product ID can be passed in a public method.
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;


        private final DataRepository mRepository;

        public Factory(@NonNull Application application) {
            mApplication = application;
            mRepository = ((BasicApp) application).getRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new PersonViewModel(mRepository);
        }
    }
}

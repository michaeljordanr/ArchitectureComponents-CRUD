package com.michaeljordanr.androidroom_crud.person.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import com.michaeljordanr.androidroom_crud.R;
import com.michaeljordanr.androidroom_crud.databinding.FragmentPersonBinding;
import com.michaeljordanr.androidroom_crud.person.db.entity.PersonEntity;
import com.michaeljordanr.androidroom_crud.person.viewmodel.PersonViewModel;

public class PersonFragment extends Fragment {

    PersonAdapter personAdapter;

    FragmentPersonBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_person, container, false);
        personAdapter = new PersonAdapter();
        binding.peopleList.setAdapter(personAdapter);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        PersonViewModel.Factory factory = new PersonViewModel.Factory(getActivity().getApplication());
        final PersonViewModel model = ViewModelProviders.of(this, factory)
                .get(PersonViewModel.class);

        subscribeToModel(model);
    }

    private void subscribeToModel(final PersonViewModel model) {

        // Observe product data
        model.getObservablePersons().observe(this, new Observer<List<PersonEntity>>() {
            @Override
            public void onChanged(@Nullable List<PersonEntity> personEntities) {
                if(personEntities != null){
                    personAdapter.setPersonList(personEntities);
                    personAdapter.notifyDataSetChanged();
                }

                binding.executePendingBindings();
            }
        });
    }
}

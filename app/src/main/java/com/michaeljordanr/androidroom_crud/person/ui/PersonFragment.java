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
import android.widget.ArrayAdapter;
import android.widget.Toast;

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
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.spinner_style,
                new String[]{"Teste","Teste","Teste","Teste","Teste"});
        binding.sp.setAdapter(adapter);
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
        model.getObservablePersons().observe(this, personEntities -> {
            if(personEntities != null && personEntities.size() > 0){
                personAdapter.setPersonList(personEntities);
            }else{
                personAdapter.setPersonList(null);
                Toast.makeText(getActivity(), "All deleted", Toast.LENGTH_SHORT).show();
            }

            personAdapter.notifyDataSetChanged();
            binding.executePendingBindings();
        });
    }
}

package com.michaeljordanr.androidroom_crud.person.ui;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import com.michaeljordanr.androidroom_crud.R;
import com.michaeljordanr.androidroom_crud.databinding.PersonItemBinding;
import com.michaeljordanr.androidroom_crud.person.model.Person;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder>  {

    List<? extends Person> mPersonList;

    public void setPersonList(final List<? extends Person> personList){
        this.mPersonList = personList;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PersonItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.person_item,
                        parent, false);

        return new PersonViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        holder.binding.setPerson(mPersonList.get(position));
    }

    @Override
    public int getItemCount() {
        return mPersonList == null ? 0 : mPersonList.size();
    }

    static class PersonViewHolder extends RecyclerView.ViewHolder {

        PersonItemBinding binding;

        public PersonViewHolder(PersonItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

package com.michaeljordanr.androidroom_crud.person.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.transition.Slide;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.List;

import com.michaeljordanr.androidroom_crud.R;
import com.michaeljordanr.androidroom_crud.databinding.ActivityMainBinding;
import com.michaeljordanr.androidroom_crud.person.db.entity.PersonEntity;
import com.michaeljordanr.androidroom_crud.person.viewmodel.PersonViewModel;
import com.michaeljordanr.androidroom_crud.utilities.DataGenerator;
import com.tooltip.Tooltip;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private PersonViewModel model;

    private static String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);

        Tooltip tooltip = new Tooltip.Builder(binding.bt1)
                .setBackgroundColor(getResources().getColor(R.color.purple_531e6d))
                .setTextColor(getResources().getColor(android.R.color.white))
                .setTypeface(Typeface.DEFAULT_BOLD)
                .setText("Hello tooltip")
                .show();

        //DataGenerator.with(database).generatePeople();


        PersonViewModel.Factory factory = new PersonViewModel.Factory(getApplication());
        model = ViewModelProviders.of(this, factory)
                .get(PersonViewModel.class);


        binding.fab2.setOnClickListener(v -> {
            model.deleteAll();

        });


        if (savedInstanceState == null) {
            PersonFragment fragment = new PersonFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_list_people, fragment, PersonFragment.class.getName()).commit();
        }

        Spinner sp = findViewById(R.id.sp);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item, new String[]{"teste", "teste", "teste"});
        sp.setAdapter(adapter);

    }

    public void onOnClickInsert(View view){
        if(firstNameValidation() && lastNameValidation()) {
            PersonEntity person = new PersonEntity();
            person.setFirstName(binding.edFirstName.getText().toString());
            person.setLastName(binding.edLastName.getText().toString());
            model.insert(person);
            Toast.makeText(getApplication(), person.firstName + " - " + person.lastName,
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void nextActivity(View view){
        Slide slide = new Slide();
        slide.setSlideEdge(Gravity.TOP);

       // TransitionManager.beginDelayedTransition(this);
        startActivity(new Intent(this, Main2Activity.class));
    }

    private boolean firstNameValidation(){
        if (TextUtils.isEmpty(binding.edFirstName.getText().toString())){
            Drawable myIcon = getResources().getDrawable(R.drawable.ic_done);
            myIcon.setBounds(0, 0, myIcon.getIntrinsicWidth(), myIcon.getIntrinsicHeight());
            binding.edFirstName.setError("first name is required!", myIcon);
            return false;
        }else {
            binding.tlFirstName.setErrorEnabled(false);
        }
        return true;
    }

    private boolean lastNameValidation(){
        if (TextUtils.isEmpty(binding.edLastName.getText().toString())){
            Drawable myIcon = getResources().getDrawable(R.drawable.ic_done);
            myIcon.setBounds(0, 0, myIcon.getIntrinsicWidth(), myIcon.getIntrinsicHeight());
            binding.edLastName.setError("last name is required!", myIcon);
            return false;
        }else {
            binding.tlLastName.setErrorEnabled(false);
        }
        return true;
    }

}
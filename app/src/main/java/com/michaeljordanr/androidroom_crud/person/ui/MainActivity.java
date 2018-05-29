package com.michaeljordanr.androidroom_crud.person.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import java.util.List;

import com.michaeljordanr.androidroom_crud.R;
import com.michaeljordanr.androidroom_crud.database.AppDataBase;
import com.michaeljordanr.androidroom_crud.databinding.ActivityMainBinding;
import com.michaeljordanr.androidroom_crud.person.db.entity.PersonEntity;
import com.michaeljordanr.androidroom_crud.person.viewmodel.PersonViewModel;
import com.michaeljordanr.androidroom_crud.utilities.DataGenerator;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FloatingActionButton floatingActionButton;
    FloatingActionButton floatingActionButton2;
    EditText edFirstName;
    EditText edLastName;

    private static String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //DataGenerator.with(database).generatePeople();
        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton2 = findViewById(R.id.fab2);
        edFirstName = findViewById(R.id.ed_first_name);
        edLastName = findViewById(R.id.ed_last_name);

        PersonViewModel.Factory factory = new PersonViewModel.Factory(getApplication());
        final PersonViewModel model = ViewModelProviders.of(this, factory)
                .get(PersonViewModel.class);

        floatingActionButton.setOnClickListener(v -> {
            PersonEntity person = new PersonEntity();
            person.setFirstName(edFirstName.getText().toString());
            person.setLastName(edLastName.getText().toString());
            model.insert(person);
            Toast.makeText(getApplication(), person.firstName + " - " + person.lastName,
                    Toast.LENGTH_SHORT).show();
        });

        floatingActionButton2.setOnClickListener(v -> model.deleteAll());


        if (savedInstanceState == null) {
            PersonFragment fragment = new PersonFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_list_people, fragment, PersonFragment.class.getName()).commit();
        }

    }


//        // Observe comments
//        model.getComments().observe(this, new Observer<List<CommentEntity>>() {
//            @Override
//            public void onChanged(@Nullable List<CommentEntity> commentEntities) {
//                if (commentEntities != null) {
//                    mBinding.setIsLoading(false);
//                    mCommentAdapter.setCommentList(commentEntities);
//                } else {
//                    mBinding.setIsLoading(true);
//                }
//            }
//        });
}
package com.android.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;
import java.util.Map;

/**
 * Created by Karun on 8/14/16.
 */
public class Activity_ReportIssue extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Fragment mFragment;
    Toolbar toolbar;
    ActionBar actionBar;
    Button button;
    EditText editText;
    String storeSelected ="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportissue);

        toolbar=(Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


            Spinner spinner = (Spinner) findViewById(R.id.select_stores);
    // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.select_stores, android.R.layout.simple_spinner_item);
    // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    // Apply the adapter to the spinner
            spinner.setAdapter(adapter);

        storeSelected = spinner.getSelectedItem().toString();

        editText = (EditText)findViewById(R.id.issue);



        button = (Button)findViewById(R.id.take_picture);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                onCameraButtonClicked();
            }
        });

    }

    public void onCameraButtonClicked(){
            mFragment = ImageUploadFragment.newInstance(storeSelected, editText.getText().toString());
        button.setEnabled(false);
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_reportissue, mFragment).commit();

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    /*@Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, "recyclerFrag", mFragment);
        }*/
    }




package com.android.project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;


public class Activity_AddPage extends AppCompatActivity implements Fragment_List.OnListItemSelectedListener2 {
    private Fragment mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_addpage);
        mContent=Fragment_AddPage.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_addPage, mContent)
                .commit();
    }

    @Override
    public void onListItemSelected(int value) {

        switch (value) {
            case 0:

                break;
        }

    }
}

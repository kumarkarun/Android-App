package com.android.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.Map;

/**
 * Created by Karun on 8/10/16.
 */
public class Activity_CheckOut extends AppCompatActivity {

    Fragment mFragment;
    Toolbar toolbar;
    ActionBar actionBar;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent checkOut = getIntent();
        final List<Map<String, ?>> movie = (List<Map<String, ?>>)checkOut.getSerializableExtra("movie");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        toolbar=(Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if(savedInstanceState!=null){
            if(getSupportFragmentManager().getFragment(savedInstanceState,"recyclerFrag") != null){
                mFragment = getSupportFragmentManager().getFragment(savedInstanceState,"recyclerFrag");
            }else
                mFragment = CheckOutRecyclerViewFragment.newInstance(movie);
        }else
            mFragment = CheckOutRecyclerViewFragment.newInstance(movie);
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_checkout_container, mFragment).commit();

        button = (Button)findViewById(R.id.place_order);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                onPlaceOrderButtonClicked(movie);
            }
        });

    }

    public void onPlaceOrderButtonClicked(List<Map<String, ?>> movie){
        Log.d("OrderPlaced>>","Order Place button clicked");
        String order="";
        for(Map<String,?> item:movie){
            order = order +"\n"+"Name : " +item.get("name")+"\n"+"Price : " +item.get("price")+"\n";

        }
        order = order + "\n"+"\n"+"The Items will be delivered in 2 days!";
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"grocerryapp@gmail.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, "Order placed");
        email.putExtra(Intent.EXTRA_TEXT,order);
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Choose an Email client :"));

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, "recyclerFrag", mFragment);
        }
    }


}



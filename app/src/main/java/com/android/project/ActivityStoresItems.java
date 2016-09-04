package com.android.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Karun on 8/13/16.
 */
public class ActivityStoresItems extends AppCompatActivity implements StoreItems_Fragment_RecyclerView.OnCheckoutClickedListener,StoreItems_Fragment_RecyclerView.OnItemClickedListener{

    Fragment mFragment;
    Toolbar mToolBar;
    ActionBar mActionBar;
    Button button;
    FloatingActionButton fabrv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent storesItems = getIntent();
        final String storeId = (String)storesItems.getSerializableExtra("vId");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storesitems);

        mToolBar = (Toolbar) findViewById(R.id.rv_toolbar);
        setSupportActionBar(mToolBar);
        mActionBar = getSupportActionBar();

        fabrv1=(FloatingActionButton) findViewById(R.id.fabrv);
        assert fabrv1 != null;
        fabrv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(findViewById(R.id.recycler_container),"Testing FAB!!", Snackbar.LENGTH_SHORT).show();
            }
        });
        fabrv1.hide();

       /* toolbar=(Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/

        if(savedInstanceState!=null){
            if(getSupportFragmentManager().getFragment(savedInstanceState,"recyclerFrag") != null){
                mFragment = getSupportFragmentManager().getFragment(savedInstanceState,"recyclerFrag");
            }else
                mFragment = StoreItems_Fragment_RecyclerView.newInstance();
        }else
            mFragment = StoreItems_Fragment_RecyclerView.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.recycler_container, mFragment).commit();

        /*button = (Button)findViewById(R.id.place_order);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                onPlaceOrderButtonClicked(movie);
            }
        });*/

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, "recyclerFrag", mFragment);
        }
    }


    @Override
    public void onCheckOutClicked(List<Map<String, ?>> movie) {
        Log.d("InsideMain>>>>","Check Out Clicked");
        /*mContent = CheckOutRecyclerViewFragment.newInstance(movie);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.recycler_container_storesitems, mContent)
                .commit();*/
        Intent intent = new Intent(this, Activity_CheckOut.class);
        intent.putExtra("movie", (Serializable) movie);
        startActivity(intent);

    }

    @Override
    public void onItemClicked(HashMap<String, ?> movie) {
        Log.d("thismovieClicked>>>",movie.get("name").toString());
        Intent myIntent = new Intent(this,detailview_activity.class);
        Log.d("Checking","if calls");
        myIntent.putExtra("item",movie);
        startActivity(myIntent);
    }
}

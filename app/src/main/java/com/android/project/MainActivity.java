package com.android.project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.google.android.gms.maps.model.LatLng;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements StoresList_Fragment_RecyclerView.OnCardItemClickedListener/*, StoreItems_Fragment_RecyclerView.OnCheckoutClickedListener,StoreItems_Fragment_RecyclerView.OnItemClickedListener*/, NavigationView.OnNavigationItemSelectedListener , StoresList_Fragment_RecyclerView.onButtonItemClickListener{
    private Fragment mContent;
    private Fragment mContent1;
    Toolbar mToolBar;
    android.support.v7.app.ActionBar mActionBar;
    DrawerLayout drawerLayout;
    private static final String TAG_ADD = "sortAdd";
    Intent intent;
    FloatingActionButton fabrv1;


    String coordinates[];
    private Firebase expFirebaseRef;
    private Firebase firebaseUserDataRef;
    private Firebase interestedRef;
    public  Firebase FIREBASE_SERVER;
    public LatLng destination;
    LatLng destLocation;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recycler_container);
        Intent intent = this.getIntent();
        Bundle bundle= intent.getExtras();
        String name = bundle.get("name").toString();
        String email = bundle.get("email").toString();


        mToolBar = (Toolbar) findViewById(R.id.rv_toolbar);
        setSupportActionBar(mToolBar);
        mActionBar = getSupportActionBar();




        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        FIREBASE_SERVER =  new Firebase("https://grocerryapp-acf0e.firebaseio.com/");
        /*fabrv1=(FloatingActionButton) findViewById(R.id.fabrv);
        assert fabrv1 != null;
        fabrv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(findViewById(R.id.recycler_container_storesitems),"Testing FAB!!", Snackbar.LENGTH_SHORT).show();
            }
        });
        fabrv1.hide();*/

        if (savedInstanceState != null) {
            if (getSupportFragmentManager().getFragment(savedInstanceState, "mContent") != null) {
                mContent = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
            } else {
                mContent = StoresList_Fragment_RecyclerView.newInstance();
            }
        } else {
            mContent = StoresList_Fragment_RecyclerView.newInstance();
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.recycler_container, mContent)
                .commit();

        ImageView icon = new ImageView(this); // Create an icon
        icon.setImageResource(R.drawable.ic_action_new);




        NavigationView navigationView = (android.support.design.widget.NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);


        View header = navigationView.getHeaderView(0);
        TextView name1 = (TextView)header.findViewById(R.id.menu_item1);
        TextView email1 = (TextView)header.findViewById(R.id.menu_item2);
        if(name.equals("abc"))
        {
            name1.setText(email);
        }
        else
        {
            name1.setText(name);
        }
        email1.setText(email);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        ActionBarDrawerToggle toggler = new ActionBarDrawerToggle(this,drawerLayout,mToolBar,R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(toggler);
        getSupportActionBar().setHomeButtonEnabled(true);
        toggler.syncState();



    }




    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mContent.isAdded())
            getSupportFragmentManager().putFragment(outState, "mContent", mContent);
    }

    @Override
    public void onCardItemClicked(View v, int position, String vId) {
        Log.d( "onCardItemClicked:vID: ",vId);
        PopulateDB populateDB = new PopulateDB(vId);
        PopulateStoresReviewDB populateStoresReviewDB= new PopulateStoresReviewDB(vId);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*mContent = StoreItems_Fragment_RecyclerView.newInstance();
        getSupportFragmentManager().beginTransaction()
        .replace(R.id.recycler_container_storesitems, mContent)
        .commit();*/
        Intent storesItemsIntent = new Intent(this, ActivityStoresItems.class);
        startActivity(storesItemsIntent);
    }

  /*  @Override
    public void onItemClicked(HashMap<String,?> movie) {
        Log.d("thismovieClicked>>>",movie.get("name").toString());
        Intent myIntent = new Intent(this,detailview_activity.class);
        Log.d("Checking","if calls");
        myIntent.putExtra("item",movie);
        startActivity(myIntent);
    }

    @Override
    public void onCheckOutClicked(List<Map<String, ?>> movie) {
        Log.d("InsideMain>>>>","Check Out Clicked");
        *//*mContent = CheckOutRecyclerViewFragment.newInstance(movie);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.recycler_container_storesitems, mContent)
                .commit();*//*
        Intent intent = new Intent(this, Activity_CheckOut.class);
        intent.putExtra("movie", (Serializable) movie);
        startActivity(intent);
    }*/

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        int id = item.getItemId();
        switch (id){

            case R.id.task1:
                intent = new Intent(this, FlipViewActivity.class);
                startActivity(intent);
                break;

            case R.id.task2:
                String vId = "abc";
                /*mContent = ImageUploadFragment.newInstance(vId);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.recycler_container, mContent)
                        .commit();*/
                intent = new Intent(this, Activity_ReportIssue.class);
                startActivity(intent);
                break;

            case R.id.about:
                intent = new Intent(this, ActivityViewPager.class);
                startActivity(intent);
                break;

            case R.id.logout:
                intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                break;

            default:
                //fragment = AboutMeFragment.newInstance();
                //getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityContainer,
                //        fragment).addToBackStack(null).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    public void firebaseData(String siD){
        firebaseUserDataRef = FIREBASE_SERVER.child("storesinformation");
        interestedRef = firebaseUserDataRef.child(siD);
        Query eventsDetailsQuery = interestedRef.orderByKey().equalTo("destination");

        eventsDetailsQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s)
            {
                Log.i(" Value of cordinates", "onChildAdded:----------------- " + (String)dataSnapshot.getValue());
                coordinates = ((String) dataSnapshot.getValue()).split(",");
                destLocation=new LatLng((Double.parseDouble(coordinates[0])) ,(Double.parseDouble(coordinates[1])));
                if(destLocation != null){
                    if(progressDialog.isShowing())
                        progressDialog.dismiss();
                    mContent= googlemapFragment.newInstance(dataSnapshot.getValue().toString());
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.recycler_container, mContent)
                            .commit();

                }

            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }


        });

    }




    @Override
    public void onButtonItemClicked(String storeId) {

        Log.d( "Checking buton clicked:",storeId);
        progressDialog = new ProgressDialog(this,
                R.style.AppTheme);

        progressDialog.getWindow().setGravity(Gravity.CENTER);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        firebaseData(storeId);

        // mContent= googlemapFragment.newInstance(storeId);
        // getSupportFragmentManager().beginTransaction()
        //       .replace(R.id.recycler_container, mContent)
        //     .commit();
    }
}

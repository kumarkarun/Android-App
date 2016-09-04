package com.android.project;

import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PopulateDB {

    public  static String FIREBASE_SERVER =  "https://grocerryapp-acf0e.firebaseio.com/";
    //Firebase mRef = new Firebase(FIREBASE_SERVER);
    public static List<Map<String,?>> movieList = new ArrayList<Map<String, ?>>();

    public List<Map<String,?>> getMovieList (){
        Log.d("MovieList>>>>>>>>>>",movieList.toString());
        return movieList;
    }


    public PopulateDB(String storeId)
    {
        String url = FIREBASE_SERVER+storeId;
        Firebase mRef = new Firebase(url);
        movieList.clear();
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot populate : dataSnapshot.getChildren()){
                    HashMap movie = new HashMap();
                    String name = (String)populate.child("name").getValue();
                    Log.d("name@@@>>>>>>>",name);
                    movie.put("name", name);
                    String brand = (String)populate.child("brand").getValue();
                    movie.put("brand",brand);
                    String organic = (String)populate.child("organic").getValue();
                    movie.put("organic",organic);
                    String price = (String)populate.child("price").getValue();
                    movie.put("price",price);
                    String url = (String)populate.child("image").getValue();
                    movie.put("url",url);
                    String link = (String)populate.child("link").getValue();
                    movie.put("link",link);
                    String description = (String)populate.child("description").getValue();
                    movie.put("description",description);
                    String primaryId = (String)populate.child("primaryID").getValue();
                    movie.put("primaryID",primaryId);
                    String review = (String)populate.child("review").getValue();
                    movie.put("review",review);
                    String type = (String)populate.child("type").getValue();
                    movie.put("type",type);
                    String nutrition = (String)populate.child("nutrition").getValue();
                    movie.put("nutrition",nutrition);



                    movie.put("selection",false);


                    movieList.add(movie);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.w("getUser:onCancelled", firebaseError.toException());
            }
        });
    }


}

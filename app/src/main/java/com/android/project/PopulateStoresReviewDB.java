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


public class PopulateStoresReviewDB {

    public  static String FIREBASE_SERVER =  "https://grocerryapp-acf0e.firebaseio.com/storesreview/";
    //Firebase mRef = new Firebase(FIREBASE_SERVER);
    public static List<Map<String,?>> storeReviewList = new ArrayList<Map<String, ?>>();

    public List<Map<String,?>> getMovieList (){
        Log.d("storeReviewList>>>>>>>>", storeReviewList.toString());
        return storeReviewList;
    }


    public PopulateStoresReviewDB(String storeId)
    {
        String url = FIREBASE_SERVER+storeId;
        Firebase mRef = new Firebase(url);
        storeReviewList.clear();
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot populate : dataSnapshot.getChildren()){
                    HashMap userReview = new HashMap();

                    String name = (String)populate.child("name").getValue();
                    Log.d("name@@@>>>>>>>",name);
                    userReview.put("name", name);
                    String email = (String)populate.child("email").getValue();
                    userReview.put("email",email);
                    String reviews = (String)populate.child("reviews").getValue();
                    userReview.put("review",reviews);
                    String image = (String)populate.child("image").getValue();
                    userReview.put("image",image);


                    storeReviewList.add(userReview);

                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.w("getUser:onCancelled", firebaseError.toException());
            }
        });
    }


}

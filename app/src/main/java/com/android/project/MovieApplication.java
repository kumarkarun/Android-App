package com.android.project;

import android.app.Application;

import com.firebase.client.Firebase;

public class MovieApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        // other setup code
    }

}

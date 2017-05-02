package com.ankesh.instasplit;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by I324832 on 5/1/2017.
 */

public class FireBaseConnectivity extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);

    }
}

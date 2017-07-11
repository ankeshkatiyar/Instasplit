package com.ankesh.instasplit;

import android.app.Application;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by I324832 on 5/1/2017.
 */

public class FireBaseConnectivity extends Application {
    public static String uid;
    FirebaseAuth firebaseAuth;
    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
        firebaseAuth = FirebaseAuth.getInstance();
        try{
            uid = firebaseAuth.getCurrentUser().getUid();
        }
        catch (NullPointerException npe){

        }


    }
}

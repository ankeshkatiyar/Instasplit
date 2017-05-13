package com.ankesh.instasplit.Firebase;

import android.util.Log;
import android.widget.Toast;

import com.ankesh.instasplit.AddFriendActivity;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;


public class FirebaseRead {
    private  Map<String, Object> valuesCollected;
    private boolean isPresent = false;
    String mainKey,mainValue;
    private  static DataSnapshot dataSnapshotMain ;


    public boolean isValuePresent(DatabaseReference databaseReference, String key, String value) {
        mainKey = key;
        mainValue = value;


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                changeBooleanValue((Map<String, Object>) dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return  isPresent;

    }
    public String getkeyFromValue(DataSnapshot dataSnapshot, String value)
    {
        mainValue = value;
        setKeyFromValue((Map<String,Object>)dataSnapshot.getValue());
        return mainKey;
    }


    public boolean changeBooleanValue(Map<String, Object> valuesCollected) {
        for (Map.Entry<String, Object> entry : valuesCollected.entrySet()) {
            Map singleUser = (Map) entry.getValue();
            if(mainKey!=null) {


               if(singleUser.containsKey(mainKey))
               {
                   String tempValue = (String)singleUser.get(mainKey);
                   if(mainValue.equals(tempValue))
                   {
                       return true;
                   }
               }
            }
            else
            {


                if(singleUser.containsValue(mainValue))
                {
                    return true;
                }
            }


        }
        return false;
    }

    public void setKeyFromValue(Map<String, Object> valuesCollected) {

        for (Map.Entry<String, Object> entry : valuesCollected.entrySet()) {


            Log.i("MainValue",mainValue);
            Log.i("MainValue",entry.getValue().toString());
            String temo = entry.getValue().toString();


            if((entry.getValue().toString()).equals(mainValue))
            {
                String someKey = entry.getKey().toString();
                this.mainKey = someKey;
                Log.i("MainKey",mainKey);

            }



        }


    }
}


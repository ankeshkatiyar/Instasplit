package com.ankesh.instasplit.Database;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.ankesh.instasplit.FireBaseConnectivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by I324832 on 6/17/2017.
 */

public class InsertAllDataINDB {
    Map<String,Object> usersData;

    public InsertAllDataINDB(final Context context)
    {
        ContentValues contentValues = new ContentValues();
        ContentValues contentValues1 = new ContentValues();
        contentValues.put(InstaSplitContract.Users.COL_NAME_1,"Gkjl218tdZMsmEvq89zCjU7hcz83");
        contentValues.put(InstaSplitContract.Users.COL_NAME_2,"Alka");
        contentValues.put(InstaSplitContract.Users.COL_NAME_3,"Hathimare");
        contentValues.put(InstaSplitContract.Users.COL_NAME_4,"alka@gmail.com");
        contentValues.put(InstaSplitContract.Users.COL_NAME_5,"8095887017");
        contentValues1.put(InstaSplitContract.Users.COL_NAME_6,"123");
        contentValues1.put(InstaSplitContract.Users.COL_NAME_1,"F63ffLXOvqeLSmWd1aiJ7fmnNmX2");
        contentValues1.put(InstaSplitContract.Users.COL_NAME_2,"Ankesh");
        contentValues1.put(InstaSplitContract.Users.COL_NAME_3,"Katiyar");
        contentValues1.put(InstaSplitContract.Users.COL_NAME_4,"ank@gmail.com");
        contentValues1.put(InstaSplitContract.Users.COL_NAME_5,"8050625250");
       contentValues1.put(InstaSplitContract.Users.COL_NAME_6,"123");

        String where = "";
        String column[] = new String[10];
        column[0]="*";

        ArrayList<ContentValues> values  = new ArrayList<ContentValues>();
        InstaSplitDBUpdate instaSplitDBUpdate = new InstaSplitDBUpdate(context);
        instaSplitDBUpdate.dbInsert("Users",contentValues);
        instaSplitDBUpdate.dbInsert("Users",contentValues1);
       // values = instaSplitDBUpdate.dbRead("Users",column,where);
       // String query = "Delete from Users";
        //instaSplitDBUpdate.dbExec(query);



        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userReference = databaseReference.child("Users/"+ FireBaseConnectivity.uid);
        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    usersData = (Map<String, Object>) dataSnapshot.getValue();
                Log.i("usersdata",usersData.toString());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

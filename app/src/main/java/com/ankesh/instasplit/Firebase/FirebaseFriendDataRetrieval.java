package com.ankesh.instasplit.Firebase;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ankesh.instasplit.Adapters.FriendsListAdapter;
import com.ankesh.instasplit.Database.InstaSplitContract;
import com.ankesh.instasplit.Database.InstaSplitDBUpdate;
import com.ankesh.instasplit.FireBaseConnectivity;
import com.ankesh.instasplit.Models.FriendsListAttributes;
import com.ankesh.instasplit.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;


public class FirebaseFriendDataRetrieval extends AsyncTask<Void, Void, ArrayList<FriendsListAttributes>> {

    private ArrayList<FriendsListAttributes> listViewAttributes = new ArrayList<>();
    private Map<String, Object> tempFriend;
    private Context context;
    private DatabaseReference friendName, databaseReference;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    ProgressDialog progressDialog;
    private Map<String, Object> friendsData;
    private DatabaseReference myFriendsReference;
    private View actualView;
    private ContentValues contentValues;
    private ContentValues friendPersonalValues;

    public FirebaseFriendDataRetrieval(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Please Wait");
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

    }

    @Override
    protected ArrayList<FriendsListAttributes> doInBackground(Void... view) {


        databaseReference = FirebaseDatabase.getInstance().getReference();
        myFriendsReference = databaseReference.child("Users/" + FireBaseConnectivity.uid + "/MyFriends");

        databaseReference = FirebaseDatabase.getInstance().getReference();
        try {

                Log.i("Firebase","FirebaseFriendDataRetrieval Outside");
                myFriendsReference.addValueEventListener(new ValueEventListener() {

                    
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        friendsData = (Map<String, Object>) dataSnapshot.getValue();
                        Log.i("Firebase","FirebaseFriendDataRetrieval Inside + "+ FireBaseConnectivity.uid);
                        //Log.i("Firebase", friendsData.toString());
                        if (friendsData != null) {
                            addFriendsToUI(friendsData);
                        } else {
                            Toast.makeText(context, "No Friends", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        } catch (Exception npe) {
            Toast.makeText(context, "No Friends Added", Toast.LENGTH_SHORT).show();

        }

        return listViewAttributes;

    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(ArrayList<FriendsListAttributes> friendsListAttributes) {

        progressDialog.dismiss();


    }

    private void addFriendsToUI(Map<String, Object> valuesCollected) {
        try {


            for (final Map.Entry<String, Object> singleFriend : valuesCollected.entrySet()) {

                final Map newFriend = (Map) singleFriend.getValue();
                final long moneyOwed = (Long) newFriend.get("owes");

                friendName = databaseReference.child("Users/" + singleFriend.getKey());

                friendName.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tempFriend = (Map<String, Object>) dataSnapshot.getValue();
                        listViewAttributes.add(new FriendsListAttributes(R.drawable.ankesh, tempFriend.get("first_name").toString(), moneyOwed));
                        contentValues = new ContentValues();
                        Log.i("Firebase","Temp Friend" + tempFriend.toString());
                        friendPersonalValues = new ContentValues();
                        contentValues.put(InstaSplitContract.Friends.COL_NAME_1, FireBaseConnectivity.uid);
                        contentValues.put(InstaSplitContract.Friends.COL_NAME_2, singleFriend.getKey());
                        contentValues.put(InstaSplitContract.Friends.COL_NAME_3, "20.03.2017");
                        contentValues.put(InstaSplitContract.Friends.COL_NAME_4, Long.toString(moneyOwed));
                        friendPersonalValues.put(InstaSplitContract.Users.COL_NAME_1, singleFriend.getKey());
                        friendPersonalValues.put(InstaSplitContract.Users.COL_NAME_2, tempFriend.get("first_name").toString());
                        friendPersonalValues.put(InstaSplitContract.Users.COL_NAME_3, tempFriend.get("last_name").toString());
                        //friendPersonalValues.put(InstaSplitContract.Users.COL_NAME_4, tempFriend.get("email").toString());
                        friendPersonalValues.put(InstaSplitContract.Users.COL_NAME_5, tempFriend.get("mobile_number").toString());
                        friendPersonalValues.put(InstaSplitContract.Users.COL_NAME_6, 1);
                        InstaSplitDBUpdate instaSplitDBUpdate = new InstaSplitDBUpdate(context);
                        if (instaSplitDBUpdate.dbInsert("Friends", contentValues)) {
                            Log.i("Firebase", "Friend and User Inserted in the database" + contentValues.toString());
                        }
                        if (instaSplitDBUpdate.dbInsert("Users", friendPersonalValues)) {
                            Log.i("Firebase", "Friend Personal data Inserted in the database" + friendPersonalValues.toString());
                        }
                        Log.i("Indian", Integer.toString(listViewAttributes.size()));

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }


        } catch (NullPointerException npe) {
            Toast.makeText(context, "No Friends ", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();

        }


    }

}
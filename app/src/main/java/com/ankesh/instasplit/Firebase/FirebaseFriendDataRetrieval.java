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


public class FirebaseFriendDataRetrieval extends AsyncTask<View, Void, ArrayList<FriendsListAttributes>> {

    private ArrayList<FriendsListAttributes> listViewAttributes = new ArrayList<>();
    private Map<String, Object> tempFriend;
    private Context context;
    private DatabaseReference friendName, databaseReference;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    ProgressDialog progressDialog;
    private Map<String, Object> friendsData;
    public FriendAttributes delegate = null;
    private DatabaseReference myFriendsReference;
    private View actualView;

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
    protected ArrayList<FriendsListAttributes> doInBackground(View... view) {

        String[] column_name = new String[1];
        column_name[0]="friend_id";
        ArrayList<ContentValues> values  = new ArrayList<ContentValues>();

        InstaSplitDBUpdate instaSplitDBUpdate = new InstaSplitDBUpdate(context);
        String query = "SELECT displaypic ,firstname, lastname, moneyowes FROM Users INNER JOIN Friends ON Users.id = Friends.friend_id WHERE  Friends.id = " + FireBaseConnectivity.uid;
        values = instaSplitDBUpdate.dbExec(query);
        int size = values.size();
        for(int i =0 ;i < size ;i++)
        {
            listViewAttributes.add(new FriendsListAttributes(Integer.parseInt(values.get(i).get("displaypic").toString())
                    ,values.get(i).get("firstname").toString(),Long.parseLong(values.get(i).get("moneyowes").toString())));
        }


        if(listViewAttributes.isEmpty()) {

            databaseReference = FirebaseDatabase.getInstance().getReference();
            myFriendsReference = databaseReference.child("Users/" + FireBaseConnectivity.uid + "/my_Friends");

            databaseReference = FirebaseDatabase.getInstance().getReference();
            try {

                synchronized (this) {

                    myFriendsReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            friendsData = (Map<String, Object>) dataSnapshot.getValue();
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
                }
            } catch (Exception npe) {
                Toast.makeText(context, "No Friends Added", Toast.LENGTH_SHORT).show();

            }

            while (listViewAttributes.size() == 0) {
                Log.i("Indian", "Hello");
            }
            actualView = view[0];
        }
        return listViewAttributes;

    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(ArrayList<FriendsListAttributes> friendsListAttributes) {



        adapter = new FriendsListAdapter(listViewAttributes);
        recyclerView = (RecyclerView)actualView.findViewById(R.id.friendsList);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        progressDialog.dismiss();




    }

    private void addFriendsToUI(Map<String, Object> valuesCollected) {
        try {


            for (Map.Entry<String, Object> singleFriend : valuesCollected.entrySet()) {

                final Map newFriend = (Map) singleFriend.getValue();
                final long moneyOwed  =(Long)newFriend.get("owes");

                friendName = databaseReference.child("Users/" + singleFriend.getKey());

                friendName.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tempFriend = (Map<String, Object>) dataSnapshot.getValue();
                        listViewAttributes.add(new FriendsListAttributes(R.drawable.ankesh, tempFriend.get("first_name").toString(),moneyOwed));
                        Log.i("Indian",Integer.toString(listViewAttributes.size()));

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
package com.ankesh.instasplit.Firebase;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.ankesh.instasplit.Adapters.FriendsListAdapter;
import com.ankesh.instasplit.Models.FriendsListAttributes;
import com.ankesh.instasplit.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by I324832 on 5/14/2017.
 */

//public class FirebaseAsyncTask extends AsyncTask<DatabaseReference,Void,ArrayList<FriendsListAttributes>> {
//
//    public interface AsyncResponse{
//        void processFinish(ArrayList<FriendsListAttributes> values);
//    }
//
//    public AsyncResponse delegate = null;
//    private ArrayList<FriendsListAttributes> listViewAttributes = new ArrayList<>();
//    Map<String , Object> valuesCollected,tempFriend;
//    Context context;
//    DatabaseReference friendName,databaseReference,temRef;
//    private RecyclerView recyclerView;
//    private RecyclerView.Adapter adapter;
//    private RecyclerView.LayoutManager layoutManager;
//
//    public FirebaseAsyncTask(Context context)
//    {
//        this.context = context;
//    }
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
////        ProgressDialog progressDialog = new ProgressDialog(context);
////        progressDialog.setTitle("Please Wait");
////        progressDialog.setIndeterminate(true);
////        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
////        progressDialog.show();
//
//    }
//
//    @Override
//    protected ArrayList<FriendsListAttributes> doInBackground(DatabaseReference... myFriendsReference) {
//        databaseReference = FirebaseDatabase.getInstance().getReference();
//        temRef = myFriendsReference[0];
//        Log.i("AttributesInTask",myFriendsReference[0].toString());
//        myFriendsReference[0].addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                tempFriend = (Map<String, Object>) dataSnapshot.getValue();
//                Log.i("AttributesInTas",tempFriend.toString());
//                addFriendsToUI(tempFriend);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//
////        myFriendsReference[0].addListenerForSingleValueEvent(new ValueEventListener() {
////            @Override
////            public void onDataChange(DataSnapshot dataSnapshot) {
////
////
////                tempFriend = (Map<String, Object>) dataSnapshot.getValue();
////               // Log.i("AttributesInTas",tempFriend.toString());
////
////
////                addFriendsToUI(tempFriend);
////            }
////
////            @Override
////            public void onCancelled(DatabaseError databaseError) {
////
////            }
////        });
//
//      //  Log.i("AttributesInTask",listViewAttributes.toString());
//        return listViewAttributes;
//    }
//
//    @Override
//    protected void onProgressUpdate(Void... values) {
//        super.onProgressUpdate(values);
//    }
//
//    @Override
//    protected void onPostExecute(ArrayList<FriendsListAttributes> friendsListAttributes) {
//        recyclerView = (RecyclerView) findViewById(R.id.friendsList);
//        adapter = new FriendsListAdapter(listViewAttributes);
//        layoutManager = new LinearLayoutManager(context);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(adapter);
//
//    }
//
//    public void addFriendsToUI(Map<String, Object> valuesCollected) {
//
//
//        for (Map.Entry<String, Object> singleFriend : valuesCollected.entrySet()) {
//            Map friend = (Map) singleFriend.getValue();
//            friendName = databaseReference.child("Users/" + singleFriend.getKey());
//
//            friendName.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    tempFriend = (Map<String, Object>) dataSnapshot.getValue();
//
//                    addAtributesToListView(tempFriend);
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
//
//
//
//        }
//
//    }
//    public void addAtributesToListView(Map<String,Object> tempFriend)
//    {
//
//        listViewAttributes.add(new FriendsListAttributes(R.drawable.ankesh, tempFriend.get("first_name").toString(), 123));
//    }
//}

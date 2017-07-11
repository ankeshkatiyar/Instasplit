package com.ankesh.instasplit.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.ankesh.instasplit.Adapters.FriendsListAdapter;
import com.ankesh.instasplit.AddFriendActivity;
import com.ankesh.instasplit.Database.InsertAllDataINDB;
import com.ankesh.instasplit.FireBaseConnectivity;
import com.ankesh.instasplit.Firebase.FirebaseFriendDataRetrieval;
import com.ankesh.instasplit.Firebase.FriendAttributes;
import com.ankesh.instasplit.Models.FriendsListAttributes;
import com.ankesh.instasplit.OldListView.FriendsList;
import com.ankesh.instasplit.R;
import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsFragment extends Fragment  {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<FriendsListAttributes> listViewAttributes = new ArrayList<>();
    private ListView listView;
    private ArrayList<FriendsList> arrayList = new ArrayList<>();
    private Button addFriend;
    private static int count = 0;
    private Map<String, Object> friendsData, tempFriend, friend;
    private DatabaseReference databaseReference;
    private View view;

    private DatabaseReference myFriendsRefernce, friendName;

    public FriendsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Firebase.setAndroidContext(getContext());

        try {

            view = inflater.inflate(R.layout.fragment_friends, container, false);
            InsertAllDataINDB insertAllDataINDB= new InsertAllDataINDB(getContext());


//            FirebaseFriendDataRetrieval firebaseFriendDataRetrieval = new FirebaseFriendDataRetrieval(getContext());
//            firebaseFriendDataRetrieval.execute(view);

            //new FirebaseFriendDataRetrieval(getContext()).execute(myFriendsRefernce);


//            recyclerView = (RecyclerView) view.findViewById(R.id.friendsList);
//            recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//                @Override
//                public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//                    return false;
//                }
//
//                @Override
//                public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//
//                }
//
//                @Override
//                public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//                }
//            });
//            Button button = (Button) view.findViewById(R.id.add_friends);
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    startActivity(new Intent(getContext(), AddFriendActivity.class));
//
//
//                }
//            });
//
//        } catch (NullPointerException npe) {
//            Toast.makeText(getContext(), "Internal error occured", Toast.LENGTH_LONG).show();
//        }
//
//        adapter = new FriendsListAdapter(listViewAttributes);
//        recyclerView = (RecyclerView) view.findViewById(R.id.friendsList);
//        layoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(adapter);
            Log.i("OnPostExecy", listViewAttributes.toString());
        }
        catch (NullPointerException npe)
        {

            Toast.makeText(getContext(), "Internal error occured", Toast.LENGTH_LONG).show();
        }

        return view;
    }
}

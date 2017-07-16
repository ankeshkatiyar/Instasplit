package com.ankesh.instasplit.Fragments;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ankesh.instasplit.Adapters.FriendsListAdapter;
import com.ankesh.instasplit.AddFriendActivity;
import com.ankesh.instasplit.Database.InstaSplitDBUpdate;
import com.ankesh.instasplit.FireBaseConnectivity;
import com.ankesh.instasplit.Firebase.FirebaseFriendDataRetrieval;
import com.ankesh.instasplit.MainActivity;
import com.ankesh.instasplit.Models.FriendsListAttributes;
import com.ankesh.instasplit.R;
import com.firebase.client.Firebase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<FriendsListAttributes> listViewAttributes = new ArrayList<>();
    private View view;
    Button addFriends;

    public FriendsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Firebase.setAndroidContext(getContext());
        view = inflater.inflate(R.layout.fragment_friends, container, false);


        addFriends = (Button)view.findViewById(R.id.add_friends);
        addFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),AddFriendActivity.class);
                startActivity(intent);
                getActivity().finish();
                try {
                    FriendsFragment.this.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }


            }
        });

        try {
            listViewAttributes.clear();

            getFriendsFromDatabase();
            if (listViewAttributes.isEmpty()) {
                //FirebaseFriendDataRetrieval firebaseFriendDataRetrieval = new FirebaseFriendDataRetrieval(getContext());
                //firebaseFriendDataRetrieval.execute(view);
                //Log.i("Firebase", "Reading from Firebase");
            } else {
                //setting the values in the UI
                adapter = new FriendsListAdapter(listViewAttributes);
                recyclerView = (RecyclerView) view.findViewById(R.id.friendsList);
                layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);
                Log.i("Firebase", "Reading from database");
            }
        } catch (NullPointerException npe) {

            Toast.makeText(getContext(), "Internal error occured", Toast.LENGTH_LONG).show();
        }


        return view;
    }

    public void getFriendsFromDatabase() {

////
        String[] column_name = new String[4];
        column_name[0] = "firstname";
        column_name[1] = "lastname";
        column_name[2] = "moneyowes";
        String where = "Friends.id = '" + FireBaseConnectivity.uid + "'";
        ArrayList<ContentValues> values = new ArrayList<ContentValues>();
//
         InstaSplitDBUpdate instaSplitDBUpdate = new InstaSplitDBUpdate(getContext());
//        instaSplitDBUpdate.dbDelete("Delete from Friends");
//        instaSplitDBUpdate.dbDelete("Delete from Users");
//
        values = instaSplitDBUpdate.dbRead("Users", column_name, where, "INNER JOIN Friends ON Users.id = Friends.friend_id");
        int size = values.size();
        for (int i = 0; i < size; i++) {
            listViewAttributes.add(new FriendsListAttributes(1, values.get(i).get("firstname").toString(), Long.parseLong(values.get(i).get("moneyowes").toString())));
        }
//
//
    }
}

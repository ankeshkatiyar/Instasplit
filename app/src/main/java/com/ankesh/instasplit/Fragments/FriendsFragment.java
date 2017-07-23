package com.ankesh.instasplit.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.ankesh.instasplit.ExtraFunctions.FriendRecyclerViewItems;
import com.ankesh.instasplit.HelperClasses.FriendHelperClass;
import com.ankesh.instasplit.Models.FriendsListAttributes;
import com.ankesh.instasplit.R;
import com.firebase.client.Firebase;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<FriendsListAttributes> listViewAttributes = new ArrayList<>();
    private View view;
    private int REQUEST_CODE;
    Button addFriends;

    public FriendsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Firebase.setAndroidContext(getContext());
        view = inflater.inflate(R.layout.fragment_friends, container, false);


        addFriends = (Button) view.findViewById(R.id.add_friends);
        addFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddFriendActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                getActivity().finish();
                try {
                    FriendsFragment.this.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }


            }
        });

        try {
            if(FriendRecyclerViewItems.friendsListAttributesArrayList.isEmpty()) {
                listViewAttributes.clear();
                listViewAttributes = new FriendHelperClass(getContext()).getFriends();
                FriendRecyclerViewItems.friendsListAttributesArrayList = listViewAttributes ;
            }
            else{
                listViewAttributes.clear();
                listViewAttributes = FriendRecyclerViewItems.friendsListAttributesArrayList;
            }

            Log.i("Firebase1","List"+ listViewAttributes.toString());
            adapter = new FriendsListAdapter(listViewAttributes );
            if(FriendHelperClass.singleFriendListAttributes!=null){
                Log.i("Firebase1","Yo baby I am here");
                listViewAttributes.add(0,FriendHelperClass.singleFriendListAttributes);
                adapter.notifyItemInserted(listViewAttributes.size()-1);
                FriendHelperClass.singleFriendListAttributes=null;

            }
            adapter.notifyItemInserted(listViewAttributes.size()-1);
            recyclerView = (RecyclerView) view.findViewById(R.id.friendsList);
            layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);


        } catch (NullPointerException npe) {

            Toast.makeText(getContext(), "Internal error occured ", Toast.LENGTH_LONG).show();
        }



        return view;
    }

}

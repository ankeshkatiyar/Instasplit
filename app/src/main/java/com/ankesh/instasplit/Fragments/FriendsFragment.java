package com.ankesh.instasplit.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ankesh.instasplit.Adapters.FriendsListAdapter;
import com.ankesh.instasplit.Models.FriendsListAttributes;
import com.ankesh.instasplit.OldListView.FriendsList;
import com.ankesh.instasplit.R;

import java.util.ArrayList;




/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<FriendsListAttributes> listViewAttributes = new ArrayList<>();
    private ListView listView;
    private ArrayList<FriendsList> arrayList = new ArrayList<>();
    private  static int  count =0;

    public FriendsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
       /* View view =  inflater.inflate(R.layout.fragment_friends, container, false);


        listView = (ListView) view.findViewById(R.id.friendsList);
        if(count == 0 || count==1 ) {
            for (int i = 0; i < 20; i++) {
                arrayList.add(new FriendsList("Ankesh and Akku", " Ankesh and Akku", count));
            }
        }
        count = 1;

        FriendsCustomAdapter friendsCustomAdapter = new FriendsCustomAdapter(getActivity(),arrayList);

        listView.setAdapter(friendsCustomAdapter);
*/

        View view =  inflater.inflate(R.layout.fragment_friends, container, false);
        if(count == 0) {
            for (int i = 0; i < 10; i++) {

                listViewAttributes.add(new FriendsListAttributes(R.drawable.ankesh, "Ankesh", 240));
            }
            count =1 ;
        }
        recyclerView = (RecyclerView)view.findViewById(R.id.friendsList);
        adapter = new FriendsListAdapter(listViewAttributes);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        return  view;
    }


}

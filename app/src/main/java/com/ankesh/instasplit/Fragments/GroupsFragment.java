package com.ankesh.instasplit.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ankesh.instasplit.Adapters.GroupsListAdapter;
import com.ankesh.instasplit.Models.GroupListAttributes;
import com.ankesh.instasplit.OldListView.FriendsList;
import com.ankesh.instasplit.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupsFragment extends Fragment {
    private ListView listView;
    private static int countGroup =0;
    private ArrayList<FriendsList> arrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<GroupListAttributes> listViewAttributes = new ArrayList<>();

    public GroupsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       /* View view = inflater.inflate(R.layout.fragment_friends, container, false);
        listView = (ListView)view.findViewById(R.id.friendsList);
        if(countGroup  == 0) {
            for (int i = 0; i < 2; i++) {
                arrayList.add(new FriendsList("Group", "Group", countGroup ));
            }
        }
        countGroup = 1;

        FriendsCustomAdapter friendsCustomAdapter = new FriendsCustomAdapter(getActivity(),arrayList);

        listView.setAdapter(friendsCustomAdapter);*/
        View view =  inflater.inflate(R.layout.fragment_groups, container, false);
        for(int i =0;i<10;i++) {

            listViewAttributes.add(new GroupListAttributes(R.drawable.ankesh,"F3",120));
        }
        recyclerView = (RecyclerView)view.findViewById(R.id.groupList);
        adapter = new GroupsListAdapter(listViewAttributes);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        return  view;
    }

    }

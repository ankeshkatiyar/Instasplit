package com.ankesh.instasplit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupsFragment extends Fragment {
    private ListView listView;
    private static int countGroup =0;
    private ArrayList<FriendsList> arrayList = new ArrayList<>();

    public GroupsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_friends, container, false);
        listView = (ListView)view.findViewById(R.id.friendsList);
        if(countGroup  == 0) {
            for (int i = 0; i < 2; i++) {
                arrayList.add(new FriendsList("Group", "Group", countGroup ));
            }
        }
        countGroup = 1;

        FriendsCustomAdapter friendsCustomAdapter = new FriendsCustomAdapter(getActivity(),arrayList);

        listView.setAdapter(friendsCustomAdapter);

        return  view;
    }

}

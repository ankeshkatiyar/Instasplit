package com.ankesh.instasplit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;




/**
 * A simple {@link Fragment} subclass.
 */
public class Friends extends Fragment {

    private ListView listView;
    private ArrayList<FriendsList> arrayList = new ArrayList<>();
    private  static int  count =0;

    public Friends() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_friends, container, false);


        listView = (ListView) view.findViewById(R.id.friendsList);
        if(count == 0 || count==1 ) {
            for (int i = 0; i < 20; i++) {
                arrayList.add(new FriendsList("Ankesh and Akku", " Ankesh and Akku", count));
            }
        }
        count = 1;

        FriendsCustomAdapter friendsCustomAdapter = new FriendsCustomAdapter(getActivity(),arrayList);

        listView.setAdapter(friendsCustomAdapter);


        return  view;
    }


}

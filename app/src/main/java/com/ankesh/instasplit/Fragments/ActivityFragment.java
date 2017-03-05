package com.ankesh.instasplit.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.ankesh.instasplit.Adapters.ActivitiesListAdapter;
import com.ankesh.instasplit.Models.ActivitiesListAttributes;
import com.ankesh.instasplit.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ActivitiesListAttributes> listViewAttributes = new ArrayList<>();
    private static  int count =0;
    public ActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =  inflater.inflate(R.layout.fragment_activity, container, false);
        if(count == 0) {
            for (int i = 0; i < 10; i++) {

                listViewAttributes.add(new ActivitiesListAttributes(R.drawable.ankesh, "Ankesh", 23, "Mila and egg", "Credit house ", "26 March"));
            }
            count = 1;
        }
        recyclerView = (RecyclerView)view.findViewById(R.id.activiyRecyclerView);
        adapter = new ActivitiesListAdapter(listViewAttributes);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        return view;
    }

}

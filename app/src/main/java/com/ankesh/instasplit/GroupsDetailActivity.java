package com.ankesh.instasplit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ankesh.instasplit.Adapters.GroupsDetailAdapter;
import com.ankesh.instasplit.Models.FriendsDetailAttributes;
import com.ankesh.instasplit.Models.GroupsDetailAttributes;

import java.util.ArrayList;

public class GroupsDetailActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView  totalMoneyOwed;
    private ArrayList<GroupsDetailAttributes> groupsDetailAttributes = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups_detail);
        recyclerView = (RecyclerView)findViewById(R.id.gd_recyclerview);
        for(int i=0;i<3;i++)
        {
            groupsDetailAttributes.add(new GroupsDetailAttributes(
                    R.drawable.ankesh,"Ankesh",123,"Milk and Water",345,"26 Feb"));

        }
        adapter = new GroupsDetailAdapter(groupsDetailAttributes);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.moneyowedorlent);
        for (int i = 0; i < 3; i++) {
            totalMoneyOwed = new TextView(getApplicationContext());
            int paddingPixel = 25;
            float density = getApplicationContext().getResources().getDisplayMetrics().density;
            int paddingDp = (int) (paddingPixel * density);
            totalMoneyOwed.setPadding(paddingDp, 0, 0, 0);
            totalMoneyOwed.setText("Mohammad Salman owes you 130 Rs");
            totalMoneyOwed.setTextSize(18);
            linearLayout.addView(totalMoneyOwed);


        }



    }
}

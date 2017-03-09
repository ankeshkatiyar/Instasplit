package com.ankesh.instasplit;

//ACtivity to show the details of a friend in Friends fragment.
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ankesh.instasplit.Adapters.FriendsDetailsAdapter;
import com.ankesh.instasplit.Models.FriendsDetailAttributes;

import java.util.ArrayList;

public class FriendsDetailActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<FriendsDetailAttributes> friendsDetailAttributes = new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_details);
        recyclerView = (RecyclerView) findViewById(R.id.fd_recyclerview);
        for(int i=0;i<3;i++)
        {
            friendsDetailAttributes.add(new FriendsDetailAttributes(
                    R.drawable.ankesh,"Shared",123,"Credit house","26 Feb"));

        }
        adapter = new FriendsDetailsAdapter(friendsDetailAttributes);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }
}

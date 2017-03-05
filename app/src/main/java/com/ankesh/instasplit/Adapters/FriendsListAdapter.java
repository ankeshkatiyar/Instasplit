package com.ankesh.instasplit.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ankesh.instasplit.Models.FriendsListAttributes;
import com.ankesh.instasplit.R;
import java.util.ArrayList;

/**
 * Created by I324832 on 3/5/2017.
 */


public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.RecyclerViewHolder> {
    private ArrayList<FriendsListAttributes> listViewAttributes ;

    public   FriendsListAdapter(ArrayList<FriendsListAttributes> listViewAttributes)
    {
        this.listViewAttributes = listViewAttributes;
    }
    @Override
    public FriendsListAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friends_list_layout , parent ,false);
        FriendsListAdapter.RecyclerViewHolder recyclerViewHolder = new FriendsListAdapter.RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(FriendsListAdapter.RecyclerViewHolder holder, int position) {
        holder.friendName.setText(listViewAttributes.get(position).getFriendName());
        holder.moneyOwed.setText(Integer.toString(listViewAttributes.get(position).getMoney()));
        holder.friendDisplayPic.setImageResource(listViewAttributes.get(position).getDisplayPic());
        if(listViewAttributes.get(position).getMoney()<0) {
            holder.balanceStatus.setText("You owe");
        }
        else {
            holder.balanceStatus.setText("You are owed");
        }


    }

    @Override
    public int getItemCount() {
        return listViewAttributes.size();
    }
    public static  class RecyclerViewHolder extends RecyclerView.ViewHolder{
        private TextView moneyOwed,balanceStatus,friendName;
        private ImageView friendDisplayPic;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            moneyOwed = (TextView)itemView.findViewById(R.id.moneyowed);
            friendName = (TextView)itemView.findViewById(R.id.friendname);
            balanceStatus = (TextView)itemView.findViewById(R.id.balancestatus);
            friendDisplayPic = (ImageView)itemView.findViewById(R.id.frienddisplaypic);

           ;


        }
    }
}

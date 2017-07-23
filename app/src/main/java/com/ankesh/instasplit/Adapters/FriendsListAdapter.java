package com.ankesh.instasplit.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;


import com.ankesh.instasplit.FriendsDetailActivity;
import com.ankesh.instasplit.HelperClasses.FriendHelperClass;
import com.ankesh.instasplit.MainActivity;
import com.ankesh.instasplit.Models.FriendsListAttributes;
import com.ankesh.instasplit.R;

import java.util.ArrayList;

/**
 * Created by I324832 on 3/5/2017.
 */


public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.RecyclerViewHolder> {
    private ArrayList<FriendsListAttributes> listViewAttributes;
    private Context context;
    public  static  boolean doReload = false;
    private View tempView;
    private FriendsListAdapter.RecyclerViewHolder tempHolder;

    public FriendsListAdapter(ArrayList<FriendsListAttributes> listViewAttributes) {
        this.listViewAttributes = listViewAttributes;
    }

    @Override
    public FriendsListAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.friends_list_layout, parent, false);
        tempView = view;
        FriendsListAdapter.RecyclerViewHolder recyclerViewHolder = new FriendsListAdapter.RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(FriendsListAdapter.RecyclerViewHolder holder,  int position) {
        tempHolder = holder;
        final FriendsListAttributes friendsListAttributes = listViewAttributes.get(position);
        final int currentPosition = position;
        final String id = listViewAttributes.get(position).getId();
        final String name = listViewAttributes.get(position).getFriendName();
        holder.friendName.setText(name);
        holder.moneyOwed.setText(Long.toString(listViewAttributes.get(position).getMoney()));
        // holder.friendDisplayPic.setImageResource(listViewAttributes.get(position).getDisplayPic());
        if (listViewAttributes.get(position).getMoney() < 0) {
            holder.balanceStatus.setText("You owe");
        } else {
            holder.balanceStatus.setText("You are owed");
        }
        holder.friendListlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FriendsDetailActivity.class);
                intent.putExtra("Key", "Key");
                context.startActivity(intent);
            }
        });

        //This long click will be used to delete the friend from the system and as well as the local database.
        holder.friendListlayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                alertBuilder.setTitle("Delete Entry");
                alertBuilder.setMessage("Do you want to remove " + name);
                alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FriendHelperClass friendHelperClass = new FriendHelperClass(context);
                        if(friendHelperClass.deleteFriends(id)){
                        removeItem(currentPosition,friendsListAttributes);
                        }





                    }
                }).show();
                Log.i("Firebase1","outside recyclerview");
                if(FriendHelperClass.friendAdded){
                    Log.i("Firebase1","Adding values to recyclerview");
                    listViewAttributes.add(0,FriendHelperClass.singleFriendListAttributes);
                    notifyItemInserted(listViewAttributes.size()-1);
                    FriendHelperClass.friendAdded = false;
                    FriendHelperClass.singleFriendListAttributes = null;
                }
                return true;
            }
        });


    }

    private void removeItem(int currentPosition, FriendsListAttributes friendsListAttributes) {
        int position = listViewAttributes.indexOf(friendsListAttributes);
        listViewAttributes.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return listViewAttributes.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView moneyOwed, balanceStatus, friendName;
        private ImageView friendDisplayPic;
        private ConstraintLayout friendListlayout;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            moneyOwed = (TextView) itemView.findViewById(R.id.moneyowed);
            friendName = (TextView) itemView.findViewById(R.id.friendname);
            balanceStatus = (TextView) itemView.findViewById(R.id.balancestatus);
            friendDisplayPic = (ImageView) itemView.findViewById(R.id.frienddisplaypic);
            friendListlayout = (ConstraintLayout) itemView.findViewById(R.id.friendlistlayout);


        }
    }
}

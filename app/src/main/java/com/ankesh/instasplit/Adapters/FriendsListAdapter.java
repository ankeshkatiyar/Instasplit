package com.ankesh.instasplit.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ankesh.instasplit.FriendsDetailActivity;
import com.ankesh.instasplit.HelperClasses.FriendHelperClass;
import com.ankesh.instasplit.Models.FriendsListAttributes;
import com.ankesh.instasplit.R;
import java.util.ArrayList;

/**
 * Created by I324832 on 3/5/2017.
 */


public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.RecyclerViewHolder> {
    private ArrayList<FriendsListAttributes> listViewAttributes ;
    private Context context;

    public   FriendsListAdapter(ArrayList<FriendsListAttributes> listViewAttributes)
    {
        this.listViewAttributes = listViewAttributes;
    }
    @Override
    public FriendsListAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.friends_list_layout , parent ,false);
        FriendsListAdapter.RecyclerViewHolder recyclerViewHolder = new FriendsListAdapter.RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(FriendsListAdapter.RecyclerViewHolder holder, int position) {
        holder.friendName.setText(listViewAttributes.get(position).getFriendName());
        holder.moneyOwed.setText(Long.toString(listViewAttributes.get(position).getMoney()));
       // holder.friendDisplayPic.setImageResource(listViewAttributes.get(position).getDisplayPic());
        if(listViewAttributes.get(position).getMoney()<0) {
            holder.balanceStatus.setText("You owe");
        }
        else {
            holder.balanceStatus.setText("You are owed");
        }
        holder.friendListlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,FriendsDetailActivity.class);
                intent.putExtra("Key","Key");
                context.startActivity(intent);
            }
        });
        //This long click will be used to delete the friend from the system and as well as the local database.
        holder.friendListlayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                alertBuilder.setTitle("Delete Entry");
                alertBuilder.setMessage("Do you want to delete this friend");
                alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FriendHelperClass friendHelperClass = new FriendHelperClass(context);
                        friendHelperClass.deleteFriendsFromDatabaseAndFirebase();
                    }
                }).show();
                return  true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return listViewAttributes.size();
    }
    public static  class RecyclerViewHolder extends RecyclerView.ViewHolder{
        private TextView moneyOwed,balanceStatus,friendName;
        private ImageView friendDisplayPic;
        private ConstraintLayout friendListlayout;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            moneyOwed = (TextView)itemView.findViewById(R.id.moneyowed);
            friendName = (TextView)itemView.findViewById(R.id.friendname);
            balanceStatus = (TextView)itemView.findViewById(R.id.balancestatus);
            friendDisplayPic = (ImageView)itemView.findViewById(R.id.frienddisplaypic);
            friendListlayout = (ConstraintLayout)itemView.findViewById(R.id.friendlistlayout);





        }
    }
}

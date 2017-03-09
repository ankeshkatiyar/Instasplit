package com.ankesh.instasplit.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ankesh.instasplit.Models.FriendsDetailAttributes;
import com.ankesh.instasplit.R;
import java.util.ArrayList;

/**
 * Created by I324832 on 3/5/2017.
 */


public class FriendsDetailsAdapter extends RecyclerView.Adapter<FriendsDetailsAdapter.RecyclerViewHolder> {
    private ArrayList<FriendsDetailAttributes> listViewAttributes ;

    public   FriendsDetailsAdapter(ArrayList<FriendsDetailAttributes> listViewAttributes)
    {
        this.listViewAttributes = listViewAttributes;
    }
    @Override
    public FriendsDetailsAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friends_details_layout , parent ,false);
        FriendsDetailsAdapter.RecyclerViewHolder recyclerViewHolder = new FriendsDetailsAdapter.RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(FriendsDetailsAdapter.RecyclerViewHolder holder, int position) {
        holder.dateOrTime.setText(listViewAttributes.get(position).getDateOrTime());
        holder.moneyOwed.setText(Integer.toString(listViewAttributes.get(position).getMoney()));
        holder.DisplayPic.setImageResource(listViewAttributes.get(position).getDisplayPic());
        if(listViewAttributes.get(position).getMoney()<0) {
            holder.owe.setText("You owe");
        }
        else {
            holder.owe.setText("You are owed");
        }
        holder.groupName.setText(listViewAttributes.get(position).getGroupName());
        holder.groupType.setText(listViewAttributes.get(position).getGroupType());


    }

    @Override
    public int getItemCount() {
        return listViewAttributes.size();
    }
    public static  class RecyclerViewHolder extends RecyclerView.ViewHolder{
        private TextView moneyOwed,dateOrTime,groupName,owe,groupType;
        private ImageView DisplayPic;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            dateOrTime = (TextView)itemView.findViewById(R.id.fd_date);
            groupName = (TextView)itemView.findViewById(R.id.fd_group_name);
            moneyOwed = (TextView)itemView.findViewById(R.id.fd_money_owed);
            DisplayPic = (ImageView)itemView.findViewById(R.id.fd_displaypic);
            owe = (TextView)itemView.findViewById(R.id.fd_owe);
            groupType = (TextView)itemView.findViewById(R.id.fd_group_type);





        }
    }
}

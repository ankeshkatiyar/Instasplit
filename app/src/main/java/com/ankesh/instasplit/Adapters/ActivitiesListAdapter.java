package com.ankesh.instasplit.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ankesh.instasplit.Models.ActivitiesListAttributes;
import com.ankesh.instasplit.R;

import java.util.ArrayList;


public class ActivitiesListAdapter extends RecyclerView.Adapter<ActivitiesListAdapter.RecyclerViewHolder> {
    private ArrayList<ActivitiesListAttributes> listViewAttributes;

    public ActivitiesListAdapter(ArrayList<ActivitiesListAttributes> listViewAttributes) {
        this.listViewAttributes = listViewAttributes;
    }

    @Override
    public ActivitiesListAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activities_list_layout, parent, false);
        ActivitiesListAdapter.RecyclerViewHolder recyclerViewHolder = new ActivitiesListAdapter.RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(ActivitiesListAdapter.RecyclerViewHolder holder, int position) {
        holder.itemsAdded.setText(listViewAttributes.get(position).getFriendName()
                + "added" + listViewAttributes.get(position).getItemsAdded() + "in"
                + listViewAttributes.get(position).getGroupName());

        holder.categoryDisplayPic.setImageResource(listViewAttributes.get(position).getDisplayPic());
        holder.dateOrTime.setText(listViewAttributes.get(position).getDateOrTime());
        if (listViewAttributes.get(position).getMoney() < 0) {
            holder.owedMoney.setText("You owe" +
                    Integer.toString(listViewAttributes.get(position).getMoney()));
        } else {
            holder.owedMoney.setText("You are owed" +
                    Integer.toString(listViewAttributes.get(position).getMoney()));
        }


    }

    @Override
    public int getItemCount() {
        return listViewAttributes.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView itemsAdded, dateOrTime, owedMoney;
        private ImageView categoryDisplayPic;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            itemsAdded = (TextView) itemView.findViewById(R.id.itemsadded);
            dateOrTime = (TextView) itemView.findViewById(R.id.dateortime);
            owedMoney = (TextView) itemView.findViewById(R.id.owedmoney);
            categoryDisplayPic = (ImageView) itemView.findViewById(R.id.categoryPic);


        }
    }
}
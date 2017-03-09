package com.ankesh.instasplit.Adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ankesh.instasplit.FriendsDetailActivity;
import com.ankesh.instasplit.Models.GroupListAttributes;
import com.ankesh.instasplit.Models.GroupsDetailAttributes;
import com.ankesh.instasplit.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by I324832 on 3/5/2017.
 */


public class GroupsDetailAdapter extends RecyclerView.Adapter<GroupsDetailAdapter.RecyclerViewHolder> {
    private ArrayList<GroupsDetailAttributes> listViewAttributes = new ArrayList<>();

    public GroupsDetailAdapter(ArrayList<GroupsDetailAttributes> listViewAttributes) {
        this.listViewAttributes = listViewAttributes;
    }

    @Override
    public GroupsDetailAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_details_layout, parent, false);
        GroupsDetailAdapter.RecyclerViewHolder recyclerViewHolder = new GroupsDetailAdapter.RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(GroupsDetailAdapter.RecyclerViewHolder holder, int position) {
        holder.description.setText(listViewAttributes.get(position).getDescription());
        holder.moneyLentOrBorrowed.setText(Integer.toString(listViewAttributes.get(position).getYourShare()));
        holder.dateOfSpending.setText(listViewAttributes.get(position).getDate());
        holder.paidPersonMoney.setText(listViewAttributes.get(position).getPersonPaid()
                + "paid " + listViewAttributes.get(position).getMoneyPaid());
        holder.groupDisplayPic.setImageResource(listViewAttributes.get(position).getDisplayPic());
        //condition to check if user paid the money or not
        if (true) {
            holder.lentOrBorrowed.setText("You lent");
        } else {
            holder.lentOrBorrowed.setText("You borrowed");
        }


    }

    @Override
    public int getItemCount() {
        return listViewAttributes.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView description, paidPersonMoney, dateOfSpending, moneyLentOrBorrowed, lentOrBorrowed;
        private ImageView groupDisplayPic;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            groupDisplayPic = (ImageView) itemView.findViewById(R.id.gd_displaypic);
            description = (TextView) itemView.findViewById(R.id.gd_description);
            paidPersonMoney = (TextView) itemView.findViewById(R.id.gd_paid);
            dateOfSpending = (TextView) itemView.findViewById(R.id.gd_date);
            moneyLentOrBorrowed = (TextView) itemView.findViewById(R.id.gd_moneylentorborrowed);
            lentOrBorrowed = (TextView) itemView.findViewById(R.id.gd_lentorborrowed);
           /* ConstraintLayout constraintLayout = (ConstraintLayout)itemView.findViewById(R.id.group_constraint);
            ConstraintSet set = new ConstraintSet();

            personMoney = new TextView(itemView.getContext());
            constraintLayout.addView(personMoney,0);
            set.clone(constraintLayout);
            set.connect(personMoney.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT, 60);

            set.applyTo(constraintLayout);*/

        }
    }
}

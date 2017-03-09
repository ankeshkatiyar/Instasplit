package com.ankesh.instasplit.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ankesh.instasplit.FriendsDetailActivity;
import com.ankesh.instasplit.GroupsDetailActivity;
import com.ankesh.instasplit.Models.GroupListAttributes;
import com.ankesh.instasplit.R;
import java.util.ArrayList;

/**
 * Created by I324832 on 3/5/2017.
 */


public class GroupsListAdapter extends RecyclerView.Adapter<GroupsListAdapter.RecyclerViewHolder> {
   private  ArrayList<GroupListAttributes> listViewAttributes ;
   private Context context;

    public   GroupsListAdapter(ArrayList<GroupListAttributes> listViewAttributes)
    {
        this.listViewAttributes = listViewAttributes;
    }
    @Override
    public GroupsListAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.group_list_layout , parent ,false);
        GroupsListAdapter.RecyclerViewHolder recyclerViewHolder = new GroupsListAdapter.RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(GroupsListAdapter.RecyclerViewHolder holder, int position) {
        holder.groupName.setText(listViewAttributes.get(position).getgroupName());
        holder.groupMoneyOwed.setText(Integer.toString(listViewAttributes.get(position).getgroupMoney()));
        holder.groupDisplayPic.setImageResource(listViewAttributes.get(position).getDisplayPic());
        if(listViewAttributes.get(position).getgroupMoney() < 0)
        {
            holder.groupBalanceStatus.setText("You owe");
        }
        else {
            holder.groupBalanceStatus.setText("You are owed");
        }
        holder.groupListLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,GroupsDetailActivity.class);
                intent.putExtra("Key","Key");
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listViewAttributes.size();
    }
    public static  class RecyclerViewHolder extends RecyclerView.ViewHolder{
        private TextView groupMoneyOwed,groupBalanceStatus,groupName,personMoney;
        private ImageView groupDisplayPic;
        private ConstraintLayout groupListLayout;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            groupMoneyOwed = (TextView)itemView.findViewById(R.id.groupmoneyowed);
            groupName = (TextView)itemView.findViewById(R.id.groupname);
            groupBalanceStatus = (TextView)itemView.findViewById(R.id.groupbalancestatus);
            groupDisplayPic = (ImageView)itemView.findViewById(R.id.groupdisplaypic);
            groupListLayout = (ConstraintLayout)itemView.findViewById(R.id.group_constraintlayout);

           /* ConstraintLayout constraintLayout = (ConstraintLayout)itemView.findViewById(R.id.group_constraint);
            ConstraintSet set = new ConstraintSet();

            personMoney = new TextView(itemView.getContext());
            constraintLayout.addView(personMoney,0);
            set.clone(constraintLayout);
            set.connect(personMoney.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT, 60);

            set.applyTo(constraintLayout);*/

            LinearLayout linearLayout = (LinearLayout)itemView.findViewById(R.id.spend_list_item);
            for(int i=0;i<3;i++) {
                personMoney = new TextView(itemView.getContext());
                int paddingPixel = 25;
                float density = itemView.getContext().getResources().getDisplayMetrics().density;
                int paddingDp = (int) (paddingPixel * density);
                personMoney.setPadding(paddingDp, 0, 0, 0);
                personMoney.setText("You owe me 30 rs");
                personMoney.setTextSize(18);
                linearLayout.addView(personMoney);
            }


        }
    }
}

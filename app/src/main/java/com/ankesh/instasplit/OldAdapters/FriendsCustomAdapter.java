/*
package com.ankesh.instasplit.OldAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ankesh.instasplit.OldListView.FriendsList;
import com.ankesh.instasplit.R;

import java.util.ArrayList;

*/
/**
 * Created by I324832 on 1/29/2017.
 *//*


public class FriendsCustomAdapter extends  ArrayAdapter<FriendsList> {
    private Context context;
    ArrayList<FriendsList> friendsList = null;
    public FriendsCustomAdapter(Context context, ArrayList<FriendsList> friendsList) {
        super(context , R.layout.friends_list_item , friendsList);
        this.friendsList= friendsList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.friends_list_item,parent,false);
        TextView textView = (TextView) view.findViewById(R.id.textView);

        //getting the image in the list item
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);


        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ankesh));
        //getting the price from the list item
        TextView price = (TextView) view.findViewById(R.id.price);
        Log.e("price",String.valueOf(price));
        Log.e("Name",friendsList.get(position).getName());

        textView.setText(friendsList.get(position).getName());
        price.setText(String.valueOf(friendsList.get(position).getMoney()));

        LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.list_spend);
        TextView textView1 = new TextView(getContext());
        int paddingPixel = 25;
        float density = context.getResources().getDisplayMetrics().density;
        int paddingDp = (int)(paddingPixel * density);
        textView1.setPadding(paddingDp,0,0,0);

        textView1.setText("You owe me 30 rs");
        textView1.setTextSize(18);



        return view;
    }
}

*/

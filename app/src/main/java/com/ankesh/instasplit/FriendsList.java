package com.ankesh.instasplit;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by I324832 on 1/29/2017.
 */

public class FriendsList implements Parcelable{
    private String  displayPic;
    private String name;
    private int money;
    public FriendsList(String displayPic , String name , int money)
    {
        this.displayPic = displayPic;
        this.name = name;
        this.money = money;
    }

    public FriendsList(Parcel in) {
        displayPic = in.readString();
        name = in.readString();
        money = in.readInt();
    }

    public String getDisplayPic() {
        return displayPic;
    }

    public int getMoney() {
        return money;
    }

    public String getName() {

        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(displayPic);
        dest.writeString(name);
        dest.writeInt(money);

    }
    public static final Parcelable.Creator<FriendsList> CREATOR
            = new Parcelable.Creator<FriendsList>() {
        public FriendsList createFromParcel(Parcel in) {
            return new FriendsList(in);
        }

        public FriendsList[] newArray(int size) {
            return new FriendsList[size];
        }
    };
}

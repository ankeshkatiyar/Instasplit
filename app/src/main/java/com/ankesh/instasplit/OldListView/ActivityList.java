package com.ankesh.instasplit.OldListView;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by I324832 on 1/29/2017.
 */

public class ActivityList implements Parcelable{
    private String  displayPic;
    private String personAddedMoney;
    private String groupName;
    private String description;
    private int money;
    private String date;
    public ActivityList(String displayPic , String name , int money ,String personAddedMoney ,String groupName, String description,String date )
    {
        this.displayPic = displayPic;
        this.personAddedMoney = personAddedMoney;
        this.groupName = groupName;
        this.description = description;
        this.date = date;
        this.money = money;
    }

    public ActivityList(Parcel in) {
        displayPic = in.readString();
        personAddedMoney = in.readString();
        money = in.readInt();
        date = in.readString();
        description = in.readString();
        groupName = in.readString();
    }

    public String getDisplayPic() {
        return displayPic;
    }

    public int getMoney() {
        return money;
    }

    public String getPersonAddedMoney() {

        return personAddedMoney;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(displayPic);
        dest.writeString(personAddedMoney);
        dest.writeInt(money);
        dest.writeString(description);
        dest.writeString(groupName);
        dest.writeString(date);

    }
    public static final Parcelable.Creator<ActivityList> CREATOR
            = new Parcelable.Creator<ActivityList>() {
        public ActivityList createFromParcel(Parcel in) {
            return new ActivityList(in);
        }

        public ActivityList[] newArray(int size) {
            return new ActivityList[size];
        }
    };
}

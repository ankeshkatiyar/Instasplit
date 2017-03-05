package  com.ankesh.instasplit.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by I324832 on 3/5/2017.
 */

public class ActivitiesListAttributes implements Parcelable {
    private int  displayPic;
    private String friendName;
    private int money;
    private String itemsAdded;
    private String groupName;
    private String dateOrTime;
    public ActivitiesListAttributes(int displayPic , String friendName , int money, String itemsAdded,
                                    String groupName , String dateOrTime)
    {
        this.displayPic = displayPic;
        this.friendName = friendName;
        this.money = money;
        this.itemsAdded = itemsAdded;
        this.groupName = groupName;
        this.dateOrTime = dateOrTime;
    }

    public ActivitiesListAttributes(Parcel in) {
        displayPic = in.readInt();
        money = in.readInt();
        friendName = in.readString();
        itemsAdded = in.readString();
        groupName = in.readString();
        dateOrTime = in.readString();
    }

    public int getDisplayPic() {
        return displayPic;
    }

    public int getMoney() {
        return money;
    }
    public String getFriendName() {
        return friendName;
    }
    public String getItemsAdded() {
        return itemsAdded;
    }

    public String getGroupName()
    {
        return groupName;
    }
    public String getDateOrTime()
    {
        return dateOrTime;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(displayPic);
        dest.writeInt(money);
        dest.writeString(friendName);
        dest.writeString(itemsAdded);
        dest.writeString(groupName);
        dest.writeString(dateOrTime);


    }
    public static final Parcelable.Creator< ActivitiesListAttributes> CREATOR
            = new Parcelable.Creator< ActivitiesListAttributes>() {
        public  ActivitiesListAttributes createFromParcel(Parcel in) {
            return new  ActivitiesListAttributes(in);
        }

        public  ActivitiesListAttributes[] newArray(int size) {
            return new  ActivitiesListAttributes[size];
        }
    };
}

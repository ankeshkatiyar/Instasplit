package  com.ankesh.instasplit.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by I324832 on 3/5/2017.
 */

public class GroupsDetailAttributes implements Parcelable {
    private int  displayPic;
    private int money;
    private String groupType;
    private String groupName;
    private String dateOrTime;
    public GroupsDetailAttributes(int displayPic , String groupType , int money,
                                   String groupName , String dateOrTime)
    {
        this.displayPic = displayPic;
        this.groupType = groupType;
        this.money = money;
        this.groupName = groupName;
        this.dateOrTime = dateOrTime;
    }

    public GroupsDetailAttributes(Parcel in) {
        displayPic = in.readInt();
        money = in.readInt();
        groupType = in.readString();
        groupName = in.readString();
        dateOrTime = in.readString();
    }

    public int getDisplayPic() {
        return displayPic;
    }

    public int getMoney() {
        return money;
    }
    public String getGroupType() {
        return groupType;
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
        dest.writeString(groupType);
        dest.writeString(groupName);
        dest.writeString(dateOrTime);


    }
    public static final Parcelable.Creator< GroupsDetailAttributes> CREATOR
            = new Parcelable.Creator< GroupsDetailAttributes>() {
        public  GroupsDetailAttributes createFromParcel(Parcel in) {
            return new  GroupsDetailAttributes(in);
        }

        public  GroupsDetailAttributes[] newArray(int size) {
            return new  GroupsDetailAttributes[size];
        }
    };
}
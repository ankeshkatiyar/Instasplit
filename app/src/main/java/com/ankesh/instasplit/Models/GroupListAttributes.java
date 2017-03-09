package  com.ankesh.instasplit.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by I324832 on 3/5/2017.
 */

public class GroupListAttributes implements Parcelable {
    private int  displayPic;
    private String groupName;
    private int groupMoney;
    public GroupListAttributes(int displayPic , String groupName , int groupMoney)
    {
        this.displayPic = displayPic;
        this.groupName = groupName;
        this.groupMoney = groupMoney;
    }

    public GroupListAttributes(Parcel in) {
        displayPic = in.readInt();
        groupMoney = in.readInt();
        groupName = in.readString();
    }

    public int getDisplayPic() {
        return displayPic;
    }

    public int getgroupMoney() {
        return groupMoney;
    }
    public String getgroupName() {
        return groupName;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(displayPic);
        dest.writeInt(groupMoney);
        dest.writeString(groupName);



    }
    public static final Parcelable.Creator< GroupListAttributes> CREATOR
            = new Parcelable.Creator< GroupListAttributes>() {
        public  GroupListAttributes createFromParcel(Parcel in) {
            return new  GroupListAttributes(in);
        }

        public  GroupListAttributes[] newArray(int size) {
            return new  GroupListAttributes[size];
        }
    };
}
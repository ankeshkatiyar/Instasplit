package  com.ankesh.instasplit.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by I324832 on 3/5/2017.
 */

public class FriendsListAttributes implements Parcelable {
    private int  displayPic;
    private String friendName;
    private int money;
    public FriendsListAttributes(int displayPic , String friendName , int money)
    {
        this.displayPic = displayPic;
        this.friendName = friendName;
        this.money = money;
    }

    public FriendsListAttributes(Parcel in) {
        displayPic = in.readInt();
        money = in.readInt();
        friendName = in.readString();
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



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(displayPic);
        dest.writeInt(money);
        dest.writeString(friendName);


    }
    public static final Parcelable.Creator< FriendsListAttributes> CREATOR
            = new Parcelable.Creator< FriendsListAttributes>() {
        public  FriendsListAttributes createFromParcel(Parcel in) {
            return new  FriendsListAttributes(in);
        }

        public  FriendsListAttributes[] newArray(int size) {
            return new  FriendsListAttributes[size];
        }
    };
}

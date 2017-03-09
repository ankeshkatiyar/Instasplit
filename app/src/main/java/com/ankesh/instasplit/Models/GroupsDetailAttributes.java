package  com.ankesh.instasplit.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by I324832 on 3/5/2017.
 */

public class GroupsDetailAttributes implements Parcelable {
    private int  displayPic;
    private String personPaid;
    private int moneyPaid;
    private String description;
    private int yourShare;
    private String date;
    public GroupsDetailAttributes(int displayPic , String personPaid ,
                               int moneyPaid,String description , int yourShare,String date)
    {
        this.displayPic = displayPic;
        this.personPaid = personPaid;
        this.moneyPaid = moneyPaid;
        this.description = description;
        this.yourShare = yourShare;
        this.date = date;
    }

    public GroupsDetailAttributes(Parcel in) {
        displayPic = in.readInt();
        moneyPaid = in.readInt();
        personPaid = in.readString();
        description = in.readString();
        yourShare = in.readInt();
        date = in.readString();
    }

    public int getDisplayPic() {
        return displayPic;
    }


    public String getPersonPaid() {
        return personPaid;
    }

    public int getMoneyPaid() {
        return moneyPaid;
    }

    public String getDescription() {
        return description;
    }

    public int getYourShare() {
        return yourShare;
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
        dest.writeInt(displayPic);
        dest.writeInt(moneyPaid);
        dest.writeString(personPaid);
        dest.writeString(description);
        dest.writeInt(yourShare);



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

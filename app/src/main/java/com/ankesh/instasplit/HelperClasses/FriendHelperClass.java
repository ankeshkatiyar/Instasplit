package com.ankesh.instasplit.HelperClasses;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.ankesh.instasplit.Database.InstaSplitContract;
import com.ankesh.instasplit.Database.InstaSplitDBUpdate;
import com.ankesh.instasplit.FireBaseConnectivity;
import com.ankesh.instasplit.Firebase.FirebaseRead;
import com.ankesh.instasplit.Models.FriendsListAttributes;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


public class FriendHelperClass {
    private Firebase rootReference;
    private FirebaseAuth firebaseAuth;
    private String uid, friendUId;
    private boolean isFriendAdded = false;
    private DatabaseReference databaseReference;
    private Map<String, Object> tempFriend;
    private ContentValues friendsTableData = new ContentValues();
    private ContentValues userTableData = new ContentValues();
    private String innerUId ;
    private String phoneorEmail;
    private Context context;


    public FriendHelperClass(Context context){
        this.context = context;
    }


      public ArrayList<FriendsListAttributes> getFriendsFromDatabase() {
        ArrayList<FriendsListAttributes> listViewAttributes = new ArrayList<>();

        String[] column_name = new String[4];
        column_name[0] = "firstname";
        column_name[1] = "lastname";
        column_name[2] = "moneyowes";
        String where = "Friends.id = '" + FireBaseConnectivity.uid + "'";
        ArrayList<ContentValues> values = new ArrayList<ContentValues>();
        InstaSplitDBUpdate instaSplitDBUpdate = new InstaSplitDBUpdate(context);
//        instaSplitDBUpdate.dbDelete("Delete from Friends");
//       instaSplitDBUpdate.dbDelete("Delete from Users");

        values = instaSplitDBUpdate.dbRead("Users", column_name, where, "INNER JOIN Friends ON Users.id = Friends.friend_id");
        int size = values.size();
        for (int i = 0; i < size; i++) {
            listViewAttributes.add(new FriendsListAttributes(1, values.get(i).get("firstname").toString(), Long.parseLong(values.get(i).get("moneyowes").toString())));
        }
        return  listViewAttributes;

    }



    public boolean deleteFriendsFromDatabaseAndFirebase(){
        return true;
    }




    public boolean addFriends(String value) {
        phoneorEmail = value;

        try {
            //getting the database reference
            databaseReference = FirebaseDatabase.getInstance().getReference();

            //getting the firebase auth to get the current user
            firebaseAuth = FirebaseAuth.getInstance();


            if (phoneorEmail.contains("@")) {

                DatabaseReference emailReference = databaseReference.child("UsersIdEmail");

                emailReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        if (checkIfFriendExists(dataSnapshot)) {
                            if (!checkIfFriendAlreadyAdded()) {
                                uid = firebaseAuth.getCurrentUser().getUid();
                                //check if we are not adding ourself
                                if (friendUId == uid) {
                                    Toast.makeText(context, "You cannot add yourself", Toast.LENGTH_LONG).show();
                                } else {
                                    //adding friend to the current user account
                                    addFriendsToFirebaseDatabase(phoneorEmail, "mobile_number", friendUId, uid);
                                    //adding the current user to the new friend
                                    addFriendsToFirebaseDatabase(phoneorEmail, "mobile_number", uid, friendUId);

                                }
                            } else {
                                Toast.makeText(context, "Friend already added", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(context, "Friend Not registered", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            } else {
                DatabaseReference mobileReference = databaseReference.child("UsersIdMobile");
                Log.i("Firebase","I am hereq");

                mobileReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        if (checkIfFriendExists(dataSnapshot)) {
                            if (!checkIfFriendAlreadyAdded()) {

                                if (friendUId == uid) {
                                    Toast.makeText(context, "You cannot add yourself", Toast.LENGTH_LONG).show();
                                } else {
                                    //adding friend to the current user account
                                    addFriendsToFirebaseDatabase(phoneorEmail, "mobile_number", friendUId, uid);
                                    //adding the current user to the new friend
                                    addFriendsToFirebaseDatabase(phoneorEmail, "mobile_number", uid, friendUId);
                                    //adding friend to local database
                                    addFriendToLocalDatabase(uid, friendUId, DateFormat.getDateTimeInstance().format(new Date()), 00);
                                }
                            } else {
                                Toast.makeText(context, "Friend already added", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(context, "Friend Not registered", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            return true;
        } catch (Exception e) {
            Log.i("Firebase","Exception FriendHelperClass");
            e.printStackTrace();
            return false;
        }
    }


    public void addFriendsToFirebaseDatabase(String phoneOrEmail, String value, String friendUId, String UserId) {

        rootReference = new Firebase("https://instasplit-77aa0.firebaseio.com/Users/" + UserId + "/MyFriends/" + friendUId);
        Firebase childRef = rootReference.child("activity_friend");
        childRef.child("Activity_ID").setValue("act123");
        rootReference.child("date_added").setValue(DateFormat.getDateTimeInstance().format(new Date()));
        rootReference.child("owes").setValue(00);

        Toast.makeText(context, "Friend Added", Toast.LENGTH_LONG).show();


    }


    public boolean checkIfFriendExists(DataSnapshot dataSnapshot) {
        String friendId = (new FirebaseRead()).getkeyFromValue(dataSnapshot, phoneorEmail);
        this.friendUId = friendId;
        Map<String, String> values = (Map) dataSnapshot.getValue();

        if (values.containsKey(friendUId)) {
            return true;
        }

        return false;
    }

    public boolean checkIfFriendAlreadyAdded() {
        try {

            uid = firebaseAuth.getCurrentUser().getUid();
            DatabaseReference childRef = databaseReference.child("Users").child(uid).child("MyFriends");
            childRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Map<String, Object> Users = (Map<String, Object>) dataSnapshot.getValue();
                    if(Users!=null) {
                        if (Users.containsKey(friendUId)) {
                            Log.i("Firebase","User already there");
                            isFriendAdded = true;

                        }
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            return isFriendAdded;
        }catch (NullPointerException npe)
        {

            Log.i("Firebase","Null pointer exception Add_friend_activity");
            return false;
        }


    }

    public boolean addFriendToLocalDatabase(String uid, String friend_id, String date, long owes) {

        try {

            innerUId = friend_id;

            friendsTableData.put(InstaSplitContract.Friends.COL_NAME_1, uid);
            friendsTableData.put(InstaSplitContract.Friends.COL_NAME_2, friend_id);
            friendsTableData.put(InstaSplitContract.Friends.COL_NAME_3, date);
            friendsTableData.put(InstaSplitContract.Friends.COL_NAME_4, Long.toString(owes));


            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            DatabaseReference localDatabaseRefernce =  databaseReference.child("Users/"+ friend_id);
            Log.i("Firebase",friend_id);
            Log.i("Firebase",friend_id);
            localDatabaseRefernce.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.i("Firebase","I am inside");
                    tempFriend = (Map<String, Object>) dataSnapshot.getValue();
                    if(!tempFriend.equals(null)) {
                        Log.i("Firebase", "Temp Friend is" + tempFriend.toString());
                        userTableData.put(InstaSplitContract.Users.COL_NAME_1, innerUId);
                        userTableData.put(InstaSplitContract.Users.COL_NAME_2, tempFriend.get("first_name").toString());
                        userTableData.put(InstaSplitContract.Users.COL_NAME_3, tempFriend.get("last_name").toString());
                        // userTableData.put(InstaSplitContract.Users.COL_NAME_4, tempFriend.get("email").toString());
                        userTableData.put(InstaSplitContract.Users.COL_NAME_5, tempFriend.get("mobile_number").toString());
                        userTableData.put(InstaSplitContract.Users.COL_NAME_6, 1);

                        InstaSplitDBUpdate instaSplitDBUpdate = new InstaSplitDBUpdate(context);
                        if (instaSplitDBUpdate.dbInsert("Friends", friendsTableData)) {
                            Log.i("Firebase", "Friend and User Inserted in the database" + friendsTableData.toString());
                        }
                        if (instaSplitDBUpdate.dbInsert("Users", userTableData)) {
                            Log.i("Firebase", "Friend Personal data Inserted in the database" + userTableData.toString());
                        }
                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            return true;
        } catch (NullPointerException npe) {
            Log.i("Firebase","Null Pointer Exception");
            npe.printStackTrace();
            return false;
        }
    }

}

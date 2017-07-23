package com.ankesh.instasplit.HelperClasses;

import android.app.ProgressDialog;
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
    public static boolean friendAdded = false;
    public static FriendsListAttributes singleFriendListAttributes ;
    private DatabaseReference databaseReference;
    private Map<String, Object> tempFriend;
    private String friendIdDatabase;
    private String innerUId;
    private String phoneorEmail;
    private Context context;
    private ProgressDialog progressDialog;
    public static boolean isCompleted= false;


    public FriendHelperClass(Context context) {
        this.context = context;
    }

    public ArrayList<FriendsListAttributes> getFriends() {

        ArrayList<FriendsListAttributes> listViewAttributes = new ArrayList<>();
        String id = FireBaseConnectivity.uid;
        if (FireBaseConnectivity.uid != null) {
            listViewAttributes = getFriendsFromDatabase(id);
            if (listViewAttributes.isEmpty()) {
                if (setFriendsFromFirebaseToDatabase(id)) {
                    listViewAttributes = getFriendsFromDatabase(id);
                }

            }

        }
        return listViewAttributes;
    }

    public boolean setFriendsFromFirebaseToDatabase(String id) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Please Wait");
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        Log.i("Firebase","Outside setFriendsFromFirebaseToDatabase");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users/" + id + "/MyFriends");
        if (databaseReference != null) {
            try {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tempFriend = (Map<String, Object>) dataSnapshot.getValue();
                        Log.i("Firebase","Inside setFriendsFromFirebaseToDatabase");
                        if (tempFriend != null) {
                            for (Map.Entry<String, Object> singleFriend : tempFriend.entrySet()) {
                                Map Friend = (Map) singleFriend.getValue();
                                prepareAndSendDataFromFirebaseToLocalDatabase(singleFriend.getKey());



                            }

                        }
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                return true;
            } catch (NullPointerException npe) {
                return false;
            }


        }
        return false;
    }

    public ArrayList<FriendsListAttributes> getFriendsFromDatabase(String uid) {
        ArrayList<FriendsListAttributes> listViewAttributes = new ArrayList<>();

        String[] column_name = new String[4];
        column_name[0] = "firstname";
        column_name[1] = "lastname";
        column_name[2] = "moneyowes";
        column_name[3] = "friend_id";
        String where = "Friends.id = '" + uid + "'";
        ArrayList<ContentValues> values = new ArrayList<ContentValues>();
        InstaSplitDBUpdate instaSplitDBUpdate = new InstaSplitDBUpdate(context);
//        instaSplitDBUpdate.dbDelete("Delete from Friends");
//       instaSplitDBUpdate.dbDelete("Delete from Users");

        values = instaSplitDBUpdate.dbRead("Users", column_name, where, "INNER JOIN Friends ON Users.id = Friends.friend_id");
        int size = values.size();
        for (int i = 0; i < size; i++) {
            listViewAttributes.add(new FriendsListAttributes(1, values.get(i).get("firstname").toString(), Long.parseLong(values.get(i).get("moneyowes").toString()), values.get(i).get(column_name[3]).toString()));
        }
        Log.i("Database1",listViewAttributes.toString());
        return listViewAttributes;

    }


    public boolean deleteFriends(String id) {

        if (id != null) {
            String query = "Delete FROM Friends Where friend_id = '" + id + "' and id = '" + FireBaseConnectivity.uid + "'";
            if (deleteFriendsFromDatabase(query)) {
                String path = "Users/" + FireBaseConnectivity.uid + "/MyFriends/" + id + "/";
                if(deleteFriendsFromFirebase(path)){

                }
            }
        } else {

        }


        return true;
    }


    public boolean deleteFriendsFromFirebase(String path) {
        try {
            databaseReference = FirebaseDatabase.getInstance().getReference().child(path);
            databaseReference.removeValue();
            Toast.makeText(context, "Successfully removed", Toast.LENGTH_SHORT).show();
            return true;
        } catch (NullPointerException npe) {
            Log.i("Firebase", "deleteFriendsFromFirebase");
            return false;

        }


    }


    public boolean deleteFriendsFromDatabase(String query) {
        try {
            InstaSplitDBUpdate instaSplitDBUpdate = new InstaSplitDBUpdate(context);

            if (instaSplitDBUpdate.dbDelete(query)) {
                return true;
            } else {
                Toast.makeText(context, "could not be removed from database", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (Exception e) {

            Toast.makeText(context, "Exception occured in FriendHelperClass I", Toast.LENGTH_SHORT).show();
            return false;
        }

    }


    public boolean addFriends(String value) {
        if (value != null) {
            phoneorEmail = value;
            try {
                //getting the database reference
                databaseReference = FirebaseDatabase.getInstance().getReference();

                //getting the firebase auth to get the current user
                firebaseAuth = FirebaseAuth.getInstance();


                if (phoneorEmail.contains("@")) {

                    DatabaseReference emailReference = databaseReference.child("UsersIdEmail");

                    emailReference.addValueEventListener(new ValueEventListener() {
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
                                        addFriendsToFirebaseDatabase(phoneorEmail, "mobile_number", friendUId, uid, true);
                                        //adding the current user to the new friend
                                        addFriendsToFirebaseDatabase(phoneorEmail, "mobile_number", uid, friendUId, false);

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
                    Log.i("Firebase", "About to execute onDataChange");

                    mobileReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            Log.i("Firebase", "Inside onDataChange");
                            if (checkIfFriendExists(dataSnapshot)) {
                                if (!checkIfFriendAlreadyAdded()) {

                                    if (friendUId == FireBaseConnectivity.uid) {
                                        Toast.makeText(context, "You cannot add yourself", Toast.LENGTH_LONG).show();
                                    } else {
                                        //adding friend to the current user account
                                        addFriendsToFirebaseDatabase(phoneorEmail, "mobile_number", friendUId, FireBaseConnectivity.uid, true);
                                        //adding the current user to the new friend
                                        addFriendsToFirebaseDatabase(phoneorEmail, "mobile_number", FireBaseConnectivity.uid, friendUId, false);
                                        friendAdded = true;


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
            } catch (NullPointerException npe) {
                Log.i("Firebase", "Exception FriendHelperClass");
                npe.printStackTrace();
                return false;
            }
        } else {
            Log.i("Firebase", "Null value in addFriends");
            return false;
        }
    }


    public void addFriendsToFirebaseDatabase(String phoneOrEmail, String value, String friend_Id, String UserId, boolean callLocalDatabase) {
        Log.i("Firebase", "Inside addFriendsToFiebaseDatabase");

        rootReference = new Firebase("https://instasplit-77aa0.firebaseio.com/Users/" + UserId + "/MyFriends/" + friend_Id);
        Firebase childRef = rootReference.child("activity_friend");
        childRef.child("Activity_ID").setValue("act123");
        rootReference.child("date_added").setValue(DateFormat.getDateTimeInstance().format(new Date()));
        rootReference.child("owes").setValue(00);

        if (callLocalDatabase) {
            prepareAndSendDataFromFirebaseToLocalDatabase(friendUId);

        }


    }


    public boolean addFriendToLocalDatabase(ContentValues userTableData, ContentValues friendsTableData) {

        try {
            Log.i("Firebase", "Inside addFriendToLocalDatabase");


            InstaSplitDBUpdate instaSplitDBUpdate = new InstaSplitDBUpdate(context);
            if (instaSplitDBUpdate.dbInsert("Friends", friendsTableData)) {
                Log.i("Firebase", "Friend and User Inserted in the database" + friendsTableData.toString());
            }
            if (instaSplitDBUpdate.dbInsert("Users", userTableData)) {
                Log.i("Firebase", "Friend Personal data Inserted in the database" + userTableData.toString());
            }


            return true;
        } catch (NullPointerException npe) {
            Log.i("Firebase", "Null Pointer Exception");
            npe.printStackTrace();
            return false;
        }
    }


    public boolean checkIfFriendExists(DataSnapshot dataSnapshot) {
        Log.i("Firebase", "Inside checkIfFriendExists");
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
            Log.i("Firebase", "Inside checkIfFriendAlreadyAdded");
            uid = FireBaseConnectivity.uid;
            if (friendUId != null) {
                DatabaseReference childRef = FirebaseDatabase.getInstance().getReference().child("Users/"+uid+"/MyFriends/" + friendUId);

            childRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Map<String, Object> Users = (Map<String, Object>) dataSnapshot.getValue();
                    if(Users!=null) {
                        isFriendAdded=false;
//                        if (Users.containsKey(friendUId)) {
//                            Log.i("Firebase","User already there");
//                            isFriendAdded = true;
//
//                        }
                    }
                    else{
                        isFriendAdded = true;
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            }else {
                Toast.makeText(context, "Friend id is null", Toast.LENGTH_LONG).show();
                return  true;
            }

            return isFriendAdded;
        } catch (NullPointerException npe) {

            Log.i("Firebase", "Null pointer exception Add_friend_activity");
            return true;
        }


    }

    public void prepareAndSendDataFromFirebaseToLocalDatabase(String id) {
        friendIdDatabase = id;
        DatabaseReference localDatabaseRefernce =  FirebaseDatabase.getInstance().getReference().child("Users/" + friendIdDatabase);
        Log.i("Firebase", friendIdDatabase);
        localDatabaseRefernce.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("Firebase", "I am inside");
                tempFriend = (Map<String, Object>) dataSnapshot.getValue();
                if (!tempFriend.equals(null)) {

                    Log.i("Firebase", "Temp Friend is" + tempFriend.toString());

                    ContentValues friendsTableData = new ContentValues();
                    ContentValues userTableData = new ContentValues();

                    userTableData.put(InstaSplitContract.Users.COL_NAME_1, friendIdDatabase);
                    userTableData.put(InstaSplitContract.Users.COL_NAME_2, tempFriend.get("first_name").toString());
                    userTableData.put(InstaSplitContract.Users.COL_NAME_3, tempFriend.get("last_name").toString());
                    // userTableData.put(InstaSplitContract.Users.COL_NAME_4, tempFriend.get("email").toString());
                    userTableData.put(InstaSplitContract.Users.COL_NAME_5, tempFriend.get("mobile_number").toString());
                    userTableData.put(InstaSplitContract.Users.COL_NAME_6, 1);

                    friendsTableData.put(InstaSplitContract.Friends.COL_NAME_1, FireBaseConnectivity.uid);
                    friendsTableData.put(InstaSplitContract.Friends.COL_NAME_2, friendIdDatabase);
                    friendsTableData.put(InstaSplitContract.Friends.COL_NAME_3, DateFormat.getDateTimeInstance().format(new Date()));
                    friendsTableData.put(InstaSplitContract.Friends.COL_NAME_4, "0");

                    singleFriendListAttributes = new FriendsListAttributes(1, tempFriend.get("first_name").toString(),0);
                    //adding friend to local database
                    if(addFriendToLocalDatabase(userTableData, friendsTableData)){
                        isCompleted = true;
                    }

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}

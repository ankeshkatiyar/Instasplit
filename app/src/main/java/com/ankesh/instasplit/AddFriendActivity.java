package com.ankesh.instasplit;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.ankesh.instasplit.Firebase.FirebaseRead;
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

public class AddFriendActivity extends AppCompatActivity {
    private AutoCompleteTextView friends;
    private Firebase rootReference;
    private FirebaseAuth firebaseAuth;
    private Button addFriends;
    private String phoneorEmail;
    private String uid, friendUId;
    private boolean isFriendPresent = false;
    private boolean isFriendAdded = false;
    private DatabaseReference databaseReference;
    private DataSnapshot dataSnapshotMain = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        //getting the database reference
        databaseReference = FirebaseDatabase.getInstance().getReference();

        //getting the firebase auth to get the current user
        firebaseAuth = FirebaseAuth.getInstance();


        addFriends = (Button) findViewById(R.id.add_firebase);


        //   getting the values for the autocompletetextview(contacts)
        getAutoCompleteValuesForEmailAndPhone();

        //Getting the value from the user
        friends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Formatting the value recieved according to our requirement
                //for phone numbers only 10 digits are considered and email should not have any space
                phoneorEmail = parent.getItemAtPosition(position).toString();


                if (Patterns.PHONE.matcher(phoneorEmail).matches()) {
                    phoneorEmail = phoneorEmail.replaceAll("\\s", "");
                    int numberLength = phoneorEmail.length();

                    if (numberLength > 10) {
                        phoneorEmail = phoneorEmail.substring(numberLength - 10);

                    }


                } else if (android.util.Patterns.EMAIL_ADDRESS.matcher(phoneorEmail).matches()) {
                    phoneorEmail = phoneorEmail.replaceAll("\\s", "");


                } else {
                    //getting the phone number using the nanme of the contact
                    String projection[] = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER};
                    ContentResolver cr = getContentResolver();
                    String selection = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " like'%" + phoneorEmail + "%'";
                    Cursor phNumber = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection, selection, null, null);
                    if (phNumber.moveToFirst()) {

                        phoneorEmail = phNumber.getString(0);
                        phoneorEmail = phoneorEmail.replaceAll("\\s", "");
                        int length = phoneorEmail.length();
                        if (length > 10) {
                            phoneorEmail = phoneorEmail.substring(length - 10);
                        }
                    }
                    phNumber.close();
                }


            }
        });

//Actual addition of the friend starts here
        addFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFriends(phoneorEmail);
            }
        });


    }

    //method to get all the values for autocomplete view
    public void getAutoCompleteValuesForEmailAndPhone() {
        ArrayList<String> emailPhoneNameCollection = new ArrayList<String>();
        ContentResolver cr = getContentResolver();

        Cursor emailCur = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, null, null, null);
        Cursor numberCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        Cursor nameCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);


        while (emailCur.moveToNext()) {
            String email = emailCur.getString(emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));

            emailPhoneNameCollection.add(email);
        }
        while (numberCur.moveToNext()) {
            String phone = numberCur.getString(numberCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA));
            emailPhoneNameCollection.add(phone);
        }
        while (nameCur.moveToNext()) {
            String name = nameCur.getString(nameCur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            emailPhoneNameCollection.add(name);
        }

        emailCur.close();
        numberCur.close();
        nameCur.close();
        ;

        String[] emailPhoneName = new String[emailPhoneNameCollection.size()];
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, emailPhoneName);
        emailPhoneNameCollection.toArray(emailPhoneName);


        friends = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        friends.setAdapter(adapter);
    }

    //adding the friend
    public void addFriends(String value) {


        if (value.contains("@")) {

            DatabaseReference emailReference = databaseReference.child("UsersIdEmail");

            emailReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    if (checkIfFriendExists(dataSnapshot)) {
                        if (!checkIfFriendAlreadyAdded()) {
                            uid = firebaseAuth.getCurrentUser().getUid();
                            //check if we are not adding ourself
                            if (friendUId == uid) {
                                Toast.makeText(AddFriendActivity.this, "You cannot add yourself", Toast.LENGTH_LONG).show();
                            } else {
                                addFriendsToFirebaseDatabase(phoneorEmail, "email", friendUId);
                            }
                        } else {
                            Toast.makeText(AddFriendActivity.this, "Friend already added", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(AddFriendActivity.this, "Friend Not registered", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        } else {
            DatabaseReference mobileReference = databaseReference.child("UsersIdMobile");

            mobileReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    if (checkIfFriendExists(dataSnapshot)) {
                        if (!checkIfFriendAlreadyAdded()) {

                            if (friendUId == uid) {
                                Toast.makeText(AddFriendActivity.this, "You cannot add yourself", Toast.LENGTH_LONG).show();
                            } else {
                                addFriendsToFirebaseDatabase(phoneorEmail, "mobile_number", friendUId);
                            }
                        } else {
                            Toast.makeText(AddFriendActivity.this, "Friend already added", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(AddFriendActivity.this, "Friend Not registered", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
    }


    public void addFriendsToFirebaseDatabase(String phoneOrEmail, String value, String friendUId) {

        rootReference = new Firebase("https://instasplit-77aa0.firebaseio.com/Users/"+ uid +"/MyFriends/" + friendUId);
        Firebase childRef = rootReference.child("activity_friend");
        childRef.child("Activity_ID").setValue("act123");
        rootReference.child("date_added").setValue(DateFormat.getDateTimeInstance().format(new Date()));
        rootReference.child("owes").setValue("0");
        Toast.makeText(AddFriendActivity.this, "Friend Added", Toast.LENGTH_LONG).show();

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

        uid = firebaseAuth.getCurrentUser().getUid();
        DatabaseReference childRef = databaseReference.child("Users").child(uid).child("MyFriends");
        childRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> Users = (Map<String, Object>) dataSnapshot.getValue();
                if (Users.containsKey(friendUId)) {
                    isFriendAdded = true;

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return isFriendAdded;

    }


}













package com.ankesh.instasplit;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddFriendActivity extends AppCompatActivity {
    private AutoCompleteTextView friends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);


        ArrayList<String> emailAddressCollection = new ArrayList<String>();
        ContentResolver cr = getContentResolver();

        Cursor emailCur = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, null, null, null);
        Cursor numberCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        Cursor nameCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);


        while (emailCur.moveToNext()) {
            String email = emailCur.getString(emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
            emailAddressCollection.add(email);
        }
        while (numberCur.moveToNext()) {
            String phone = numberCur.getString(numberCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA));
            emailAddressCollection.add(phone);
        }while (nameCur.moveToNext()) {
            String name = nameCur.getString(nameCur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            emailAddressCollection.add(name);
        }

        emailCur.close();
        numberCur.close();
        nameCur.close();
        ;

        String[] emailAddresses = new String[emailAddressCollection.size()];
        emailAddressCollection.toArray(emailAddresses);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, emailAddresses);
        friends = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        friends.setAdapter(adapter);

        friends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }



}



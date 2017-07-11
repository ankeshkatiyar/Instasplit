package com.ankesh.instasplit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ankesh.instasplit.Database.InsertAllDataINDB;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        InsertAllDataINDB insertAllDataINDB = new InsertAllDataINDB(getApplicationContext());
    }
}

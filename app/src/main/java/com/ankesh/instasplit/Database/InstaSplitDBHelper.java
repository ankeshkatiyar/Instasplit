package com.ankesh.instasplit.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by I324832 on 6/16/2017.
 */

public class InstaSplitDBHelper extends SQLiteOpenHelper {

    public InstaSplitDBHelper(Context context){
        super(context ,  InstaSplitContract.DATBASE_NAME , null ,   InstaSplitContract.DATBASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL( InstaSplitContract.Users.CREATE_TABLE);
        db.execSQL( InstaSplitContract.Friends.CREATE_TABLE);
        db.execSQL( InstaSplitContract.Activities.CREATE_TABLE);
        db.execSQL( InstaSplitContract.ActivitiesPartners.CREATE_TABLE);
        db.execSQL( InstaSplitContract.ActivitiesPartnersPaid.CREATE_TABLE);
        db.execSQL( InstaSplitContract.Groups.CREATE_TABLE);
        db.execSQL( InstaSplitContract.GroupPartners.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +  InstaSplitContract.Users.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +  InstaSplitContract.Activities.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +  InstaSplitContract.Friends.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +  InstaSplitContract.ActivitiesPartners.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +  InstaSplitContract.ActivitiesPartnersPaid.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +  InstaSplitContract.Groups.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +  InstaSplitContract.Groups.TABLE_NAME);

    }
}

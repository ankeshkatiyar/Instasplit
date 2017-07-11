package com.ankesh.instasplit.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;



public class InstaSplitDBUpdate {
    private SQLiteDatabase db;
    private Cursor cursor;

    public InstaSplitDBUpdate(Context context) {
         InstaSplitDBHelper helper = new  InstaSplitDBHelper(context);
        db = helper.getWritableDatabase();

    }

    public boolean dbUpdate(String tableName, ContentValues contentValues, String whereClause) {
        try {

            db.update(tableName, contentValues, whereClause, null);
        } catch (Exception e) {
            return false;
        }
        db.close();
        return true;

    }

    public ArrayList<ContentValues> dbRead(String tableName, String[] columnNames, String whereClause) {

        ArrayList<ContentValues> values = new ArrayList<>();

        Cursor cursor;
        String columnNameString = columnNames[0];
        int rowCount;

        if (!columnNames[0].equals("*")) {

            for (int i = 1; i < columnNames.length; i++) {
                columnNameString = columnNameString + "'" + columnNames[i];
            }
        }

        if(whereClause.isEmpty())
        {
            cursor = db.rawQuery("Select " + columnNameString + " from " + tableName,null);

        }
        else {
            cursor = db.rawQuery("Select " + columnNameString + " from " + tableName + " where " + whereClause, null);
        }
        try {
            cursor.moveToFirst();
            rowCount = cursor.getColumnCount();

            do {
                ContentValues contentValues = new ContentValues();
                for (int i = 0; i < rowCount; i++) {
                    contentValues.put(cursor.getColumnName(i), cursor.getString(i));
                }
                values.add(contentValues);



            } while (cursor.moveToNext());
        }
        catch (NullPointerException npe){
            return null;
        }

        cursor.close();
        db.close();
        return values;


    }
    public boolean dbInsert(String tableName,ContentValues contentValues)
    {
        try {
            db.insert(tableName, null, contentValues);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
    public ArrayList<ContentValues> dbExec(String query)
    {
        ArrayList<ContentValues> values = new ArrayList<>();

         cursor= db.rawQuery(query,null);
        int rowCount;
        rowCount = cursor.getColumnCount();
        try {

            do {
                ContentValues contentValues = new ContentValues();
                for (int i = 0; i < rowCount; i++) {
                    contentValues.put(cursor.getColumnName(i), cursor.getString(i));
                }
                values.add(contentValues);


            } while (cursor.moveToNext());
        }
        catch (NullPointerException npe)
        {
            return null;
        }
        return  values;
    }
}

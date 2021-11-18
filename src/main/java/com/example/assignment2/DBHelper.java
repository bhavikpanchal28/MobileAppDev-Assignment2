package com.example.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Location.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table LocationDetails(id NUMBER primary key, address TEXTPOSTALADDRESS, latitude NUMBERDECIMAL, longitude NUMBERDECIMAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists LocationDetails");
    }

    public Boolean insertlocationdata(String id, String address, String latitude, String longitude)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("address", address);
        contentValues.put("latitude", latitude);
        contentValues.put("longitude", longitude);
        long result = DB.insert("LocationDetails", null, contentValues);
        if (result==-1){
            return false;
        }else {
            return true;
        }
    }
    public Boolean updatelocationdata(String id, String address, String latitude, String longitude)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("address", address);
        contentValues.put("latitude", latitude);
        contentValues.put("longitude", longitude);

        Cursor cursor = DB.rawQuery("Select * from LocationDetails where id = ?", new String[] {id});
        if (cursor.getCount()>0)
        {
            long result = DB.update("LocationDetails", contentValues, "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }}


        public Boolean deletedata(String id)
        {

            SQLiteDatabase DB = this.getWritableDatabase();

            Cursor cursor = DB.rawQuery("Select * from LocationDetails where id = ?", new String[] {id});
            if (cursor.getCount()>0)
            {
                long result = DB.delete("LocationDetails", "id=?", new String[]{id});
                if (result == -1) {
                    return false;
                } else {
                    return true;
                }
                }else {
                return false;
            }
    }

        public Cursor getdata ()
        {
            SQLiteDatabase DB = this.getWritableDatabase();

            Cursor cursor = DB.rawQuery("Select * from LocationDetails", null);
           return cursor;
        }
}
package com.homestay.bipin.guest.guestList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.homestay.bipin.data.HomeStayDbHelper;

/**
 * Created by Bipin on 4/25/17.
 */

public class GuestDataBaseAdapter  {

    HomeStayDbHelper helper;
    SQLiteDatabase db;

    public static String[] guestColumn = {HomeStayDbHelper.GUEST_GID,HomeStayDbHelper.GUEST_NAME,HomeStayDbHelper.GUEST_DATE};

    public GuestDataBaseAdapter(Context context){
        helper = new HomeStayDbHelper(context);
    }

    public long insertGuest(String name,int year,int month, int day){
        db= helper.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(HomeStayDbHelper.GUEST_NAME,name);
//        contentValues.put(HomeStayDbHelper.GUEST_DATE,convertDateToString(year,month,day));
        long id = db.insertOrThrow(HomeStayDbHelper.GUEST_TABLE_NAME,null,contentValues);
        db.close();
        return id;
    }

    public long editGuest(Integer id,String name){
        db= helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(HomeStayDbHelper.GUEST_NAME,name);
//        contentValues.put(HomeStayDbHelper.GUEST_GID,name);
        long result=db.update(HomeStayDbHelper.GUEST_TABLE_NAME,
                contentValues,
                HomeStayDbHelper.GUEST_GID+"="+id,null);
        db.close();
        return result;
    }

    public Cursor getAllGuests(){
        db= helper.getWritableDatabase();
        Cursor mCursor= db.query(HomeStayDbHelper.GUEST_TABLE_NAME,null,null,null,null,null,HomeStayDbHelper.GUEST_DATE +" "+ "DESC");
        if (mCursor != null ){
            mCursor.moveToNext();
        }
        db.close();
        return mCursor;
    }




    public String convertDateToString(int year, int month, int day) {
        StringBuilder str = new StringBuilder();
        str.append(year);
        str.append("-");
        if (month < 10) {
            str.append(0 + "" + month);
        } else
            str.append(month);

        str.append("-");
        if (day < 10) {
            str.append(0 + "" + day);
        } else
            str.append(day);
        return str.toString();
    }




}

package com.homestay.bipin.menu;

import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;

import com.homestay.bipin.data.HomeStayDbHelper;

/**
 * Created by Bipin on 4/29/17.
 */

public class MenuDatabaseAdapter {

    HomeStayDbHelper helper;
    SQLiteDatabase db;


    public MenuDatabaseAdapter(Context context){
        helper= new HomeStayDbHelper(context);
    }

    public long insertMenu(String foodName,Integer price, String foodType){
        db= helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(HomeStayDbHelper.MENU_FOOD_NAME,foodName);
        contentValues.put(HomeStayDbHelper.MENU_PRICE,price);
        contentValues.put(HomeStayDbHelper.MENU_TYPE,foodType);
        long id=db.insertOrThrow(HomeStayDbHelper.MENU_TABLE_NAME,null,contentValues);
        db.close();
        return id;
    }

    public Cursor getMenuItems(){
        db= helper.getWritableDatabase();
        Cursor mCursor = db.query(HomeStayDbHelper.MENU_TABLE_NAME,null,
                null,null,null,null,HomeStayDbHelper.MENU_FOOD_NAME +" "+"DESC");
        if (mCursor != null){
            mCursor.moveToNext();
        }
        db.close();
        return mCursor;
    }
}

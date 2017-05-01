package com.homestay.bipin.data;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bipin on 4/25/17.
 */

public class HomeStayDbHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME="homestay.db";
    private static final int DATABASE_VERSION=2;

//    start of guest table configurations
    public static final String GUEST_TABLE_NAME="guest";
    public static final String GUEST_NAME="name";
    public static final String GUEST_GID="_id";
    public static final String GUEST_DATE="date";
    public static final String GUEST_CREATE_TABLE=" CREATE TABLE "+GUEST_TABLE_NAME+"(" +
            GUEST_GID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            GUEST_NAME+ " VARCHAR(255), "+
            GUEST_DATE+ " TIMESTAMP DEFAULT CURRENT_TIMESTAMP );";


    public static final String MENU_TABLE_NAME = "menu";
    public static final String MENU_FOOD_NAME = "food";
    public static final String MENU_ID = "_id";
    public static final String MENU_TYPE = "type";
    public static final String MENU_PRICE = "price";
    public static final String MENU_CREATE_TABLE = "CREATE TABLE "+MENU_TABLE_NAME+"("+
            MENU_ID+ " INTEGER PRIMARY KEY, "+
            MENU_FOOD_NAME+" VARCHAR(255), "+
            MENU_PRICE+ " INTEGER, " +
            MENU_TYPE+ " VARCHAR(255) "+
            "); ";







private Context context;

    public HomeStayDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context;

    }


    public HomeStayDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public HomeStayDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(GUEST_CREATE_TABLE);
        sqLiteDatabase.execSQL(MENU_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ GUEST_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+MENU_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}

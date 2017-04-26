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
    private static final int DATABASE_VERSION=1;

//    start of guest table configurations
    public static final String GUEST_TABLE_NAME="guest";
    public static final String GUEST_NAME="name";
    public static final String GUEST_GID="_id";
    public static final String GUEST_DATE="date";
    public static final String GUEST_CREATE_TABLE=" CREATE TABLE "+GUEST_TABLE_NAME+"(" +
            GUEST_GID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            GUEST_NAME+ " VARCHAR(255), "+
            GUEST_DATE+ " TIMESTAMP DEFAULT CURRENT_TIMESTAMP );";





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

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ GUEST_CREATE_TABLE);
        onCreate(sqLiteDatabase);
    }
}

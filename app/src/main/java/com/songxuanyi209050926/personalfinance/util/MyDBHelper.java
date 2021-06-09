package com.songxuanyi209050926.personalfinance.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

    public static final String CREATE_USERDATA="create table user("+
            "id integer primary key autoincrement,"+
            "username text not null unique ,"+
            "phone text not null unique,"+
            "password text not null)";
    private Context mContext;

    public MyDBHelper(Context context,String name,SQLiteDatabase.CursorFactory cursorFactory,int version){
        super(context,name,cursorFactory,version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERDATA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}

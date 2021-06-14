package com.songxuanyi209050926.personalfinance.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MoneyDBHelper extends SQLiteOpenHelper {

    public static final String CREATE_MONEYDATA = "create table money(" +
            "mid integer primary key autoincrement," +
            "username text not null ," +
            "proname text not null," +
            "type text not null ," +
            "remake text ," +
            "date text not null," +
            "oof real not null)";
    private Context mContext;

    public MoneyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory, int version) {
        super(context, name, cursorFactory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MONEYDATA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}

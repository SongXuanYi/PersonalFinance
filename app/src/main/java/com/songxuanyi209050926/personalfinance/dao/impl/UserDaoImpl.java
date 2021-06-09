package com.songxuanyi209050926.personalfinance.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.songxuanyi209050926.personalfinance.Bean.User;
import com.songxuanyi209050926.personalfinance.dao.UserDao;
import com.songxuanyi209050926.personalfinance.util.MyDBHelper;

public class UserDaoImpl implements UserDao {

    private MyDBHelper dbHelper;

    @Override
    public User isUserByPhoneAndPassword(Context context, String phone, String password) {
        dbHelper = new MyDBHelper(context, "user", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user where phone=? and password=?",new String[]{phone,password});
        User user = null;

        if (cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String pwd = cursor.getString(cursor.getColumnIndex("password"));
            String ph = cursor.getString(cursor.getColumnIndex("phone"));

            user = new User();

            user.setId(id);
            user.setUsername(username);
            user.setPassword(pwd);
            user.setPhone(ph);

            cursor.close();
            db.close();
        }
        return user;
    }

    @Override
    public int signUp(Context context, String username, String phone, String password) {
        dbHelper = new MyDBHelper(context, "user", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        db.execSQL("insert into user(username,phone,password) values(?,?,?)",new String[]{username,phone,password});
        ContentValues values = new ContentValues();
        values.put("username",username);
        values.put("phone",phone);
        values.put("password",password);
        int i =(int) db.insert("user",null,values);
        values.clear();
        db.close();
        return i;
    }
}

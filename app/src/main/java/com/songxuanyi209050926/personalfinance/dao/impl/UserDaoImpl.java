package com.songxuanyi209050926.personalfinance.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.songxuanyi209050926.personalfinance.Bean.Money;
import com.songxuanyi209050926.personalfinance.Bean.User;
import com.songxuanyi209050926.personalfinance.dao.UserDao;
import com.songxuanyi209050926.personalfinance.util.MoneyDBHelper;
import com.songxuanyi209050926.personalfinance.util.MyDBHelper;

public class UserDaoImpl implements UserDao {

    private MyDBHelper dbHelper;
    private MoneyDBHelper moneyDBHelper;

    @Override
    public User isUserByPhoneAndPassword(Context context, String phone, String password) {
        dbHelper = new MyDBHelper(context, "user", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user where phone=? and password=?", new String[]{phone, password});
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
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("phone", phone);
        values.put("password", password);
        int i = (int) db.insert("user", null, values);
        values.clear();
        db.close();
        return i;
    }

    @Override
    public int addMoneyInDB(Context context, Money money) {
        moneyDBHelper = new MoneyDBHelper(context,"money",null,1);
        SQLiteDatabase db =moneyDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username",money.getUsername());
        values.put("proname",money.getProName());
        values.put("type",money.getType());
        values.put("remake",money.getRemake());
        values.put("oof",money.getOof());
        int i = (int) db.insert("money",null,values);
        values.clear();
        db.close();
        return i;
    }
    public static final String CREATE_MONEYDATA = "create table money(" +
            "mid integer primary key autoincrement," +
            "username text not null ," +
            "type text not null ," +
            "remake text ," +
            "oof integer)";
}

package com.songxuanyi209050926.personalfinance.dao;

import android.content.Context;
import com.songxuanyi209050926.personalfinance.Bean.User;

public interface UserDao {

    User isUserByPhoneAndPassword(Context context, String phone, String password);

    int signUp(Context context,String username,String phone,String password);

}

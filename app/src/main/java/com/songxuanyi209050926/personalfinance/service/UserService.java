package com.songxuanyi209050926.personalfinance.service;

import android.content.Context;
import com.songxuanyi209050926.personalfinance.Bean.User;

public interface UserService {

    User isUserByPhoneAndPassword(Context context, String phone, String password);

    int signUp(Context context,String username,String phone,String password);

}

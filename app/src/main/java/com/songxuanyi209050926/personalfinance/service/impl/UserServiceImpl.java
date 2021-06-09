package com.songxuanyi209050926.personalfinance.service.impl;

import android.content.Context;
import com.songxuanyi209050926.personalfinance.Bean.User;
import com.songxuanyi209050926.personalfinance.dao.UserDao;
import com.songxuanyi209050926.personalfinance.dao.impl.UserDaoImpl;
import com.songxuanyi209050926.personalfinance.service.UserService;

public class UserServiceImpl implements UserService {

    UserDao dao = new UserDaoImpl();

    @Override
    public User isUserByPhoneAndPassword(Context context, String phone, String password) {
        return dao.isUserByPhoneAndPassword(context,phone, password);
    }

    @Override
    public int signUp(Context context, String username, String phone, String password) {
        return dao.signUp(context,username,phone,password);
    }
}

package com.songxuanyi209050926.personalfinance.dao;

import android.content.Context;
import com.songxuanyi209050926.personalfinance.Bean.Money;
import com.songxuanyi209050926.personalfinance.Bean.User;

import java.util.List;

public interface UserDao {

    User isUserByPhoneAndPassword(Context context, String phone, String password);

    int signUp(Context context,String username,String phone,String password);

    int addMoneyInDB(Context context,Money money);

    List<Money> findAllMoney(Context context,String username);
}

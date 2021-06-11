package com.songxuanyi209050926.personalfinance.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTime {
    public static String getTime(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        return formatter.format(date);
    }

}

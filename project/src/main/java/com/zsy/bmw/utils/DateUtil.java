package com.zsy.bmw.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by MAC on 29/04/2017.
 */
public class DateUtil {

    public static String getNowTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
}

package com.poshing.swr.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LiTianyi
 */
public class DateUtils {

    private static final DateUtils INSTANCE = new DateUtils();

    private DateUtils() {
    }

    public static DateUtils getInstance() {
        return INSTANCE;
    }

    public String getDate() {
        Date today = new Date();
        Date yesterday = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24);
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        int hour = Integer.parseInt(new SimpleDateFormat("HH").format(today));
        if (hour < 8) {
            //返回上一天
            return ft.format(yesterday);
        } else {
            return ft.format(today);
        }
    }

    public String getWorkingShift() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("HH");
        int hour = Integer.parseInt(ft.format(date));
        if (hour >= 8 && hour < 19) {
            return "0";
        } else {
            return "1";
        }
    }
}

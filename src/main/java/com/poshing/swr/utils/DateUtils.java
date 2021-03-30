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
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        return ft.format(date);
    }

    public String getWorkingShift() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("HH");
        int hour = Integer.parseInt(ft.format(date));
        if (hour >= 8 && hour <= 19) {
            return "0";
        } else {
            return "1";
        }
    }
}

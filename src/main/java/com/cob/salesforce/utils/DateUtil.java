package com.cob.salesforce.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Long[] startEndDatePeriod(int numberOfDays) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek() + 1);
        int noOfDays = numberOfDays; //i.e two weeks
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(cal.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
        Date startOfPeriod = cal.getTime();
        Date endOfPeriod = calendar.getTime();
        return new Long[]{startOfPeriod.getTime(), endOfPeriod.getTime()};
    }
}

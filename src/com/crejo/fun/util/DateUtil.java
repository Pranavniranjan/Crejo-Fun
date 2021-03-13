package com.crejo.fun.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static final String MM_DD_YYYY = "MM/dd/yyyy";

    public static Date formatDate(String dateString, String format){
        Date date = null;
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
           date =  dateFormat.parse(dateString);
        } catch (ParseException e){
            System.out.println("Exception occured when paring date " + e.getMessage());
        }
        return date;
    }

    public static boolean isFutureDate(Date date){
        Date currentDate = nowDate();
        return date.after(currentDate);
    }

    public static Date nowDate(){
        return new Date();
    }

    public static boolean isSameYear(Date yearToCheck, Date date){
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(yearToCheck);
        calendar2.setTime(date);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR);
    }
}

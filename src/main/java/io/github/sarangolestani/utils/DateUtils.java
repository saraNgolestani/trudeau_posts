package io.github.sarangolestani.utils;

import io.github.sarangolestani.exceptions.RequestException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    private static SimpleDateFormat simpleDateFormat;




    public static Date StringToDate(String date) throws ParseException {
        try {
            return simpleDateFormat.parse(date);
        }catch (ParseException e){
            throw new RequestException("The format of your string input is invalid!");
        }
    }

    public static String DateToString(Date date) {
        try{
            String dateString = simpleDateFormat.format(date);
            return dateString;
        }catch (Exception e){
            throw new RequestException("you");
        }

    }



}

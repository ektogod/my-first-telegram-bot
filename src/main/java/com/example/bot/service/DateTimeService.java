package com.example.bot.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;

public class DateTimeService {
    private static Date getCurrentDate(){
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        return Date.from(zonedDateTime.toInstant());
    }

    public static boolean checkNasaDate(Date nasaDate){
        Date curDate = DateTimeService.getCurrentDate();
        return nasaDate.compareTo(curDate) < 0;
    }

    public static Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

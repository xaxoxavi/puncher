package com.esliceu.puncher.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EndTimeParser implements TimeParser {


    @Override
    public DateTime addTime(Integer amount, String startingHour) {

        DateTimeFormatter hm = DateTimeFormat.forPattern("HH:mm");
        DateTime startHour = DateTime.parse(startingHour, hm);
        return startHour.plusMinutes(amount);
    }


    @Override
    public String formatTime(DateTime time) {

        return String.format("%d:%02d", (time.getHourOfDay()), (time.getMinuteOfHour()));
    }
}

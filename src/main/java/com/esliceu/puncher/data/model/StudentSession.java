package com.esliceu.puncher.data.model;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

@Entity
@DiscriminatorValue("2")
public class StudentSession extends Session{



    @ManyToOne
    private Subject subject;

    private String day;
    private String startHour;
    private Integer durada;
    private String endHour;

    public StudentSession() {}

    public String getDay() {
        return day;
    }

    public void setDay(int day) {
        Locale spanishLocale = new Locale("es", "ES");
        String dayName = DayOfWeek.of(day).getDisplayName(TextStyle.FULL,spanishLocale);
        this.day = dayName;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setDay(String day) {this.day = day;}

    public Integer getDurada() {return durada;}

    public void setDurada(Integer durada) {this.durada = durada;}

    public String getEndHour() {return endHour;}

    public void setEndHour(String endHour) {this.endHour = endHour;}
}


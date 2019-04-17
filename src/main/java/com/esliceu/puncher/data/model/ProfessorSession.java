package com.esliceu.puncher.data.model;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

@Entity
@DiscriminatorValue("1")
public class ProfessorSession extends Session{

    @ManyToOne
    @JoinColumn(name = "grupo_code")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "course_code")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "subject_code")
    private Subject subject;

    private String day;
    private String startHour;
    private String endHour;
    private Integer durada;



    public ProfessorSession() {}

    public String getDay() {
        return day;
    }

    public void setDay(Integer day) {
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



    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getDurada() {
        return durada;
    }

    public void setDurada(Integer durada) {
        this.durada = durada;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }
}
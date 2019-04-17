package com.esliceu.puncher.data.model;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Absence {

    @Id
    private Integer id;

    private String date;
    private String hour;

    private boolean isValidated = false;

    @ManyToOne
    private Session session;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Absence() {}


    public void setDate(String date) {
        this.date = date;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }



    public boolean isValidated() {
        return isValidated;
    }

    public void setValidated(boolean validated) {
        isValidated = validated;
    }


    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}

package com.esliceu.puncher.data.model;

import javax.persistence.*;
import java.util.Date;

// TODO: Make a constructor that accepts a SigningRequest as a parameter in order to set the values
@Entity //Unir con los fixajes con los ProfessorSession.
public class Signing {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private boolean checked = false;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reader reader;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Signing() {}

    public Signing(String type, Date date, Reader reader, User user) {
        this.type = type;
        this.date = date;
        this.reader = reader;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }
}

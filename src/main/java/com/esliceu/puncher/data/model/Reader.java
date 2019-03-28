package com.esliceu.puncher.data.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Reader")
public class Reader {

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy = "reader")
    private Set<Signing> signings;

    @OneToOne
    @JoinColumn(name = "group_code", nullable = false)
    private Group group;

    private String idMachine;
    public Reader() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Signing> getSignings() {
        return signings;
    }

    public void setSignings(Set<Signing> signings) {
        this.signings = signings;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setIdMachine(String idMachine) {
        this.idMachine = idMachine;
    }

    public String getIdMachine() {
        return idMachine;
    }
}

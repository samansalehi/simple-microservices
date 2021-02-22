package com.example.sam.user;

import java.util.Date;

public class User {
    private Integer id;
    private String name;
    private String fmaily;
    private Date birthDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFmaily() {
        return fmaily;
    }

    public void setFmaily(String fmaily) {
        this.fmaily = fmaily;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fmaily='" + fmaily + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}

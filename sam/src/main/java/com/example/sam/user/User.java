package com.example.sam.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
@ApiModel(description = "All the information about each user")
@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    @Size(min=2,message = "user name should be at least 2 char")
    @ApiModelProperty(notes = "user name should be at least 2 char")
    private String name;
    private String fmaily;

    @Past
    @ApiModelProperty(notes = "Birthdate should not be at the future")
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

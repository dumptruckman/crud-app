package com.aquent.crudapp.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * The person entity corresponding to the "person" table in the database.
 */
@Entity
@Table(name = "person")
public class Person implements BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @NotNull
    @Size(min = 1, max = 50, message = "First name is required with maximum length of 50")
    private String firstName;

    @Column
    @NotNull
    @Size(min = 1, max = 50, message = "Last name is required with maximum length of 50")
    private String lastName;

    @Column
    @NotNull
    @Size(min = 1, max = 50, message = "Email address is required with maximum length of 50")
    private String emailAddress;

    @Column
    @NotNull
    @Size(min = 1, max = 50, message = "Street address is required with maximum length of 50")
    private String streetAddress;

    @Column
    @NotNull
    @Size(min = 1, max = 50, message = "City is required with maximum length of 50")
    private String city;

    @Column
    @NotNull
    @Size(min = 2, max = 2, message = "State is required with length 2")
    private String state;

    @Column
    @NotNull
    @Size(min = 5, max = 5, message = "Zip code is required with length 5")
    private String zipCode;

    @Column
    @CreatedDate
    private Date createdDate;

    @Column
    @LastModifiedDate
    private Date lastModifiedDate;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    @Override
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}

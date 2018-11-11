package com.aquent.crudapp.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The person entity corresponding to the "person" table in the database.
 */
public class Person {

    private Integer personId;

    @NotNull
    @Size(min = 1, max = 50, message = "First name is required with maximum length of 50")
    private String firstName;

    @NotNull
    @Size(min = 1, max = 50, message = "Last name is required with maximum length of 50")
    private String lastName;

    @NotNull
    @Size(min = 1, max = 50, message = "Email address is required with maximum length of 50")
    private String emailAddress;

    // TODO: figure out the right way to handle nested objects
    @NotNull
    private Address address;

    @NotNull
    private Client client;

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", address=" + address +
                ", client=" + client +
                '}';
    }
}

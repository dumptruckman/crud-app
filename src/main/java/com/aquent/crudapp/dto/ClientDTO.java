package com.aquent.crudapp.dto;

import com.aquent.crudapp.constants.RegEx;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.TreeSet;

public class ClientDTO implements Comparable<ClientDTO> {

    private Long id;

    @NotNull
    @Size(min = 1, max = 50, message = "Name is required with maximum length of 50")
    private String name;

    @NotNull
    @Pattern(regexp = RegEx.PHONE_NUMBER, message = "Phone number must be a properly formatted with ten digits")
    private String phoneNumber;

    @NotNull
    @Pattern(regexp = RegEx.WEB_URL, message = "Website URL must be correctly formatted")
    @Size(min = 1, max = 50, message = "Website URL is required with a maximum length of 50")
    private String websiteUri;

    @NotNull
    @Size(min = 1, max = 50, message = "Street address is required with a maximum length of 50")
    private String streetAddress;

    @NotNull
    @Size(min = 1, max = 50, message = "City is required with a maximum length of 50")
    private String city;

    @NotNull
    @Size(min = 1, max = 2, message = "State is required with a maximum length of 2")
    private String state;

    @NotNull
    @Pattern(regexp = RegEx.ZIP_CODE, message = "Zip code is required with format XXXXX or XXXXX-XXXX")
    private String zipCode;

    private Set<PersonDTO> contacts = new TreeSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebsiteUri() {
        return websiteUri;
    }

    public void setWebsiteUri(String websiteUri) {
        this.websiteUri = websiteUri;
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

    public Set<PersonDTO> getContacts() {
        return contacts;
    }

    @Override
    public int compareTo(ClientDTO other) {
        return this.getName().compareTo(other.getName());
    }
}

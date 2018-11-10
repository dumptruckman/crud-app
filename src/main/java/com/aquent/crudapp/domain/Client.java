package com.aquent.crudapp.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The client entity corresponding to the "client" table in the database.
 */
public class Client {

    private Integer clientId;

    @NotNull
    @Size(min = 1, max = 100, message = "Company name is required with maximum length of 100")
    private String companyName;

    @NotNull
    @Size(min = 1, max = 100, message = "Website URI is required with maximum length of 50")
    private String website;

    @NotNull
    @Size(min = 7, max = 10, message = "Phone number is required with maximum length of 10")
    private String phoneNumber;

    @NotNull
    private Address physicalAddress;

    @NotNull
    private Address mailingAddress;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    @NotNull
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(@NotNull String companyName) {
        this.companyName = companyName;
    }

    @NotNull
    public String getWebsite() {
        return website;
    }

    public void setWebsite(@NotNull String website) {
        this.website = website;
    }

    @NotNull
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotNull String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @NotNull
    public Address getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(@NotNull Address physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    @NotNull
    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(@NotNull Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", companyName='" + companyName + '\'' +
                ", website='" + website + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", physicalAddress=" + physicalAddress +
                ", mailingAddress=" + mailingAddress +
                '}';
    }
}

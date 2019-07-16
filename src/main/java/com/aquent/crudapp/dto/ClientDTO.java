package com.aquent.crudapp.dto;

import com.aquent.crudapp.constants.RegEx;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.TreeSet;

@Getter
@Setter
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
    private String websiteUrl;

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

    @Override
    public int compareTo(ClientDTO other) {
        return this.name.compareTo(other.name);
    }
}

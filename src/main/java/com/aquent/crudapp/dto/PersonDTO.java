package com.aquent.crudapp.dto;

import com.aquent.crudapp.constants.RegEx;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PersonDTO implements Comparable<PersonDTO> {

    private Long id;

    @NotNull
    @Size(min = 1, max = 50, message = "First name is required with maximum length of 50")
    private String firstName;

    @NotNull
    @Size(min = 1, max = 50, message = "Last name is required with maximum length of 50")
    private String lastName;

    @NotNull
    @Pattern(regexp = RegEx.EMAIL_ADDR, message = "Email address must be in correct format")
    @Size(min = 1, max = 50, message = "Email address is required with maximum length of 50")
    private String emailAddress;

    @NotNull
    @Size(min = 1, max = 50, message = "Street address is required with maximum length of 50")
    private String streetAddress;

    @NotNull
    @Size(min = 1, max = 50, message = "City is required with maximum length of 50")
    private String city;

    @NotNull
    @Size(min = 2, max = 2, message = "State is required with length 2")
    private String state;

    @NotNull
    @Pattern(regexp = RegEx.ZIP_CODE, message = "Zip code is required with format XXXXX or XXXXX-XXXX")
    private String zipCode;

    private Long clientId;

    private String clientName;

    @Override
    public int compareTo(PersonDTO other) {
        int lastNameCompare = this.lastName.compareTo(other.lastName);
        if (lastNameCompare != 0) {
            return lastNameCompare;
        }

        return this.firstName.compareTo(other.firstName);
    }
}

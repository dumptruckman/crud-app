package com.aquent.crudapp.service;

import com.aquent.crudapp.domain.Address;
import com.aquent.crudapp.domain.Person;

import java.util.List;

/**
 * Address operations.
 */
public interface AddressService {

    /**
     * Deletes an address record by ID.
     *
     * @param id the address ID
     */
    void deleteAddress(Integer id);

    /**
     * Validates populated address data.
     *
     * @param address the values to validate
     * @return list of error messages
     */
    List<String> validateAddress(Address address);
}

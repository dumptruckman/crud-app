package com.aquent.crudapp.data.dao;

import com.aquent.crudapp.domain.Address;
import com.aquent.crudapp.domain.Person;

/**
 * Operations on the "address" table.
 */
public interface AddressDao {

    /**
     * Creates a new address record and associates it with a person.
     *
     * @param person the person to create an address record for
     * @return the new address ID
     */
    Integer createAddressForPerson(Person person);

    /**
     * Retrieves an address record for a person by ID.
     *
     * @param id the person ID
     * @return the address record
     */
    Address readAddressForPerson(Integer id);

    /**
     * Updates an existing address record.
     *
     * @param address the new values to save
     */
    void updateAddress(Address address);

    /**
     * Deletes an address record by ID.
     *
     * @param id the address ID
     */
    void deleteAddress(Integer id);
}

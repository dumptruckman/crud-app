package com.aquent.crudapp.data.dao;

import com.aquent.crudapp.domain.Address;
import com.aquent.crudapp.domain.Client;
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
     * Creates a new physical address record and associates it with a client.
     *
     * @param client the client to create a physical address record for
     * @return the new physical address ID
     */
    Integer createPhysicalAddressForClient(Client client);

    /**
     * Creates a new mailing address record and associates it with a client.
     *
     * @param client the client to create a mailing address record for
     * @return the new mailing address ID
     */
    Integer createMailingAddressForClient(Client client);

    /**
     * Retrieves an address record for a person by ID.
     *
     * @param id the person ID
     * @return the address record
     */
    Address readAddressForPerson(Integer id);

    /**
     * Retrieves a physical address record for a client by ID.
     *
     * @param id the client ID
     * @return the physical address record
     */
    Address readPhysicalAddressForClient(Integer id);

    /**
     * Retrieves a mailing address record for a client by ID.
     *
     * @param id the client ID
     * @return the mailing address record
     */
    Address readMailingAddressForClient(Integer id);

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

package com.aquent.crudapp.service;

/**
 * Service interface for managing client contacts
 */
public interface ContactService {

    /**
     * Adds a person with the supplied person id to a client
     * with the supplied client id.
     *
     * @param clientId is the id of the client
     * @param personId is the id of the person
     */
    void add(Long clientId, Long personId);


    /**
     * Removes the associated client from a person
     * with the given id.
     *
     * Since in this application a given person can
     * only be associated with one contact,
     * this is a cleaner approach than removing
     * them from the client
     *
     * @param personId is the id of the person to remove as a contact
     */
    void remove(Long personId);
}

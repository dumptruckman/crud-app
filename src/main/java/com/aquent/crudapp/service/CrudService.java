package com.aquent.crudapp.service;

import com.aquent.crudapp.exception.NotFoundException;

import java.util.Collection;

/**
 * Describes services that implement
 * standard CRUD functionality
 *
 * @param <T> is the type of resource
 *           that is handled by the service
 */
public interface CrudService<T> {

    /**
     * Lists all members of type T
     *
     * @return a list of all available items of type T
     */
   Collection<T> list();

    /**
     * Stores a new instance of type T
     *
     * @param t is the data that needs to be stored
     * @return T after storage is complete and any
     * data manipulation has occurred
     */
    T create(T t);

    /**
     * Gets an object of type T with the supplied id
     *
     * @param id is the id of the object of type T to retrieve
     * @return the object
     * @throws NotFoundException if object with the supplied id does not exist.
     */
    T get(Long id) throws NotFoundException;

    /**
     * Updates an existing object in the database
     *
     * @param t is the object that has been updated
     * @return the object after storage
     * @throws NotFoundException if the object with the supplied id does not exist.
     */
    T update(T t) throws NotFoundException;

    /**
     * Deletes the object in the database
     *
     * @param id is the id of the object to delete
     * @throws NotFoundException if the object with the supplied id does not exist
     */
    void delete(Long id) throws NotFoundException;

}

package com.aquent.crudapp.service;

import com.aquent.crudapp.dto.PersonDTO;

import java.util.Set;

/**
 * Service for manipulating person data
 *
 * Implements CrudService for data access
 * Implements Validation for....validation
 */
public interface PersonService extends CrudService<PersonDTO>, Validation<PersonDTO> {
    /**
     * Finds all people in the application who
     * do not have an associated client.
     *
     * @return the set of all people with no associated client record
     */
    public Set<PersonDTO> allUnassigned();
}

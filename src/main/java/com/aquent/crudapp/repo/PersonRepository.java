package com.aquent.crudapp.repo;

import com.aquent.crudapp.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for accessing people
 *
 * Uses built-in CrudRepository as prototype
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    Iterable<Person> findByClientIsNull();
}

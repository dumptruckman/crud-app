package com.aquent.crudapp.repo;

import com.aquent.crudapp.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Operations on the "person" table.
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}

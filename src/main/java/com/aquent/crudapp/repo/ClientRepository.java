package com.aquent.crudapp.repo;

import com.aquent.crudapp.entity.Client;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for accessing clients (companies)
 *
 * Uses built-in CrudRepository as prototype
 */
public interface ClientRepository extends CrudRepository<Client, Long> { }

package com.aquent.crudapp.repo;

import com.aquent.crudapp.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> { }

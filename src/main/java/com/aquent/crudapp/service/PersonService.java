package com.aquent.crudapp.service;

import com.aquent.crudapp.core.Validated;
import com.aquent.crudapp.dto.PersonDTO;
import com.aquent.crudapp.entity.Person;

public interface PersonService extends CrudService<PersonDTO>, Validated<PersonDTO> {
}

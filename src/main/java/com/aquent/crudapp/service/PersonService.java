package com.aquent.crudapp.service;

import com.aquent.crudapp.dto.PersonDTO;

import java.util.Set;

public interface PersonService extends CrudService<PersonDTO>, Validation<PersonDTO> {
    public Set<PersonDTO> allUnassigned();
}

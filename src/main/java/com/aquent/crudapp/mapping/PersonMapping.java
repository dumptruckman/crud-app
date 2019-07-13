package com.aquent.crudapp.mapping;

import com.aquent.crudapp.dto.PersonDTO;
import com.aquent.crudapp.entity.Person;
import com.aquent.crudapp.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonMapping implements EntityMapping<Person, PersonDTO> {

    @Override
    public Person toEntity(PersonDTO dto, Person person) {

        person.setId(dto.getId());
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setEmailAddress(dto.getEmailAddress());
        person.setStreetAddress(dto.getStreetAddress());
        person.setCity(dto.getCity());
        person.setState(dto.getState());
        person.setZipCode(dto.getZipCode());

        return person;
    }

    @Override
    public PersonDTO fromEntity(Person person) {

        PersonDTO dto = new PersonDTO();
        dto.setId(person.getId());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setEmailAddress(person.getEmailAddress());
        dto.setStreetAddress(person.getStreetAddress());
        dto.setCity(person.getCity());
        dto.setState(person.getState());
        dto.setZipCode(person.getZipCode());

        return dto;
    }
}

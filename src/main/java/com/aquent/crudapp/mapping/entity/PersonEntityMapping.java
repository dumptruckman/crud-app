package com.aquent.crudapp.mapping.entity;

import com.aquent.crudapp.dto.PersonDTO;
import com.aquent.crudapp.entity.Client;
import com.aquent.crudapp.entity.Person;
import com.aquent.crudapp.exception.NotFoundException;
import com.aquent.crudapp.repo.ClientRepository;
import com.aquent.crudapp.repo.PersonRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonEntityMapping implements EntityMapping<Person, PersonDTO> {

    @Autowired private PersonRepository personRepository;
    @Autowired private ClientRepository clientRepository;

    @Override
    public Person toEntity(PersonDTO dto, Person entity) {

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmailAddress(dto.getEmailAddress());
        entity.setStreetAddress(dto.getStreetAddress());
        entity.setCity(dto.getCity());
        entity.setState(dto.getState());
        entity.setZipCode(dto.getZipCode().replaceAll("[^0-9]", StringUtils.EMPTY));

        if (dto.getClientId() != null) {
            Client client = clientRepository.findById(dto.getClientId()).orElseThrow(() -> new NotFoundException(Client.class, dto.getClientId()));
            entity.setClient(client);
        } else {
            entity.setClient(null);
        }

        return personRepository.save(entity);
    }

    @Override
    public PersonDTO fromEntity(Person entity) {

        PersonDTO dto = new PersonDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmailAddress(entity.getEmailAddress());
        dto.setStreetAddress(entity.getStreetAddress());
        dto.setCity(entity.getCity());
        dto.setState(entity.getState());
        dto.setZipCode(entity.getZipCode());

        if (entity.getClient().isPresent()) {
            dto.setClientId(entity.getClient().get().getId());
            dto.setClientName(entity.getClient().get().getName());
        }

        return dto;
    }
}

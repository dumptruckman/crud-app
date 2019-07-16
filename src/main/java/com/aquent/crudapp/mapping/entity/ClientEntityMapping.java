package com.aquent.crudapp.mapping.entity;

import com.aquent.crudapp.dto.ClientDTO;
import com.aquent.crudapp.dto.PersonDTO;
import com.aquent.crudapp.entity.Client;
import com.aquent.crudapp.entity.Person;
import com.aquent.crudapp.exception.NotFoundException;
import com.aquent.crudapp.repo.ClientRepository;
import com.aquent.crudapp.repo.PersonRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class ClientEntityMapping implements EntityMapping<Client, ClientDTO> {

    @Autowired private ClientRepository clientRepository;
    @Autowired private PersonRepository personRepository;
    @Autowired private PersonEntityMapping personEntityMapping;

    @Override
    public Client toEntity(ClientDTO dto, Client entity) {

        entity.setName(dto.getName());
        entity.setWebsiteUrl(dto.getWebsiteUrl());
        entity.setPhoneNumber(dto.getPhoneNumber().replaceAll("[^0-9]", StringUtils.EMPTY));
        entity.setStreetAddress(dto.getStreetAddress());
        entity.setCity(dto.getCity());
        entity.setState(dto.getState());
        entity.setZipCode(dto.getZipCode());
        entity.setContacts(new HashSet<>());
        dto.getContacts().forEach((contact) -> {
            Person person = personRepository.findById(contact.getId()).orElseThrow(() ->
                    new NotFoundException(Person.class, contact.getId()));
            entity.getContacts().add(person);
        });

        return clientRepository.save(entity);
    }

    @Override
    public ClientDTO fromEntity(Client entity) {
        ClientDTO dto = new ClientDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setWebsiteUrl(entity.getWebsiteUrl());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setStreetAddress(entity.getStreetAddress());
        dto.setCity(entity.getCity());
        dto.setState(entity.getState());
        dto.setZipCode(entity.getZipCode());

        entity.getContacts().forEach((contact) -> {
            PersonDTO person = personEntityMapping.fromEntity(contact);
            dto.getContacts().add(person);
        });

        return dto;
    }
}

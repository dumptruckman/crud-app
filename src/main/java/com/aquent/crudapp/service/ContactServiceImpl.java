package com.aquent.crudapp.service;

import com.aquent.crudapp.entity.Client;
import com.aquent.crudapp.entity.Person;
import com.aquent.crudapp.exception.NotFoundException;
import com.aquent.crudapp.repo.ClientRepository;
import com.aquent.crudapp.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContactServiceImpl implements ContactService {

    @Autowired private PersonRepository personRepository;
    @Autowired private ClientRepository clientRepository;

    @Override
    public void add(Long clientId, Long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new NotFoundException(this.getClass(), personId));
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException(this.getClass(), clientId));

        person.setClient(client);
        personRepository.save(person);
    }

    @Override
    public void remove(Long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new NotFoundException(this.getClass(), personId));
        person.setClient(null);
        personRepository.save(person);
    }

}

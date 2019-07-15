package com.aquent.crudapp.service;

import com.aquent.crudapp.dto.ClientDTO;
import com.aquent.crudapp.entity.Client;
import com.aquent.crudapp.entity.Person;
import com.aquent.crudapp.exception.NotFoundException;
import com.aquent.crudapp.mapping.entity.ClientEntityMapping;
import com.aquent.crudapp.repo.ClientRepository;
import com.aquent.crudapp.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;

@Component
public class ClientServiceImpl implements ClientService {

    @Autowired
    Validator validator;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientEntityMapping clientMapping;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Set<ClientDTO> list() {
        Set<ClientDTO> result = new TreeSet<>();
       clientRepository.findAll().forEach((item) -> {
            ClientDTO dto = clientMapping.fromEntity(item);
            result.add(dto);
        });

        return result;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ClientDTO create(ClientDTO client) {
        Client entity = clientMapping.toEntity(client, new Client());

        return clientMapping.fromEntity(entity);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public ClientDTO get(Long id) {
        Client entity = clientRepository.findById(id).orElseThrow(() -> new NotFoundException(this.getClass(), id));

        return clientMapping.fromEntity(entity);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ClientDTO update(ClientDTO client) {
        Client entity = clientRepository.findById(client.getId()).orElseThrow(() -> new NotFoundException(this.getClass(), client.getId()));
        entity = clientMapping.toEntity(client, entity);

        return clientMapping.fromEntity(entity);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        Client entity = clientRepository.findById(id).orElseThrow(() -> new NotFoundException(this.getClass(), id));
        clientRepository.delete(entity);
    }

    @Override
    public List<String> validate(ClientDTO client) {
        Set<ConstraintViolation<ClientDTO>> violations = validator.validate(client);
        List<String> errors = new ArrayList<>(violations.size());
        for (ConstraintViolation<ClientDTO> violation : violations) {
            errors.add(violation.getMessage());
        }
        Collections.sort(errors);
        return errors;
    }
}
